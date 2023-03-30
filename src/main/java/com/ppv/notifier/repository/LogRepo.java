package com.ppv.notifier.repository;

import com.ppv.notifier.entity.LogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<LogInfo, Long> {
}
