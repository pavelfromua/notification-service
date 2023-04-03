package com.ppv.notifier.config;

import com.ppv.notifier.entity.User;
import com.ppv.notifier.repository.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

/**
 * Spring security configuration.
 *
 * @author Pavlo.Pavlichenko
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.antMatcher("/**")
				.authorizeRequests()
					.antMatchers("/", "/login**", "/api/notification").permitAll()
					.anyRequest().authenticated()
				.and()
					.logout().logoutSuccessUrl("/").permitAll()
				.and()
					.csrf().disable();
	}

	@Bean
	public PrincipalExtractor principalExtractor(UserRepo userRepo) {
		return map -> {
			String id = (String) map.get("sub");
			User user = userRepo.findById(id).orElseGet(() -> {
				User newUser = new User();
				newUser.setId(id);
				newUser.setName((String) map.get("name"));
				newUser.setEmail((String) map.get("email"));
				newUser.setGender((String) map.get("gender"));
				newUser.setLocale((String) map.get("locale"));

				return newUser;
			});

			user.setLastVisit(LocalDateTime.now());

			return userRepo.save(user);
		};
	}
}
