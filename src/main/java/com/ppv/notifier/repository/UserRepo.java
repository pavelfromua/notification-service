package com.ppv.notifier.repository;

import com.ppv.notifier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Pavlo.Pavlichenko
 */
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Override
    Optional<User> findById(String id);
}
