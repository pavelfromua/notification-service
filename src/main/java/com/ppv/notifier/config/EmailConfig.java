package com.ppv.notifier.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * FreeMarker Factory configuration for a basic email sending.
 *
 * @author Pavlo.Pavlichenko
 */
@Configuration
public class EmailConfig {
	@Value("${spring.freemarker.template-loader-path}")
	private String templatePath;

	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();

		fmConfigFactoryBean.setTemplateLoaderPath(templatePath);

		return fmConfigFactoryBean;
	}
}
