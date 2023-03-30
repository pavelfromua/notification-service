package com.ppv.notifier.util;

import com.ppv.notifier.entity.LogInfo;
import com.ppv.notifier.enums.LogType;
import com.ppv.notifier.repository.LogRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Example of using the AOP approach
 *
 * @author Pavlo.Pavlichenko
 */
@Aspect
@Component
public class PerformanceChecker {

    private static final String MSG_TEMPLATE = "Processed in %s sec";

    private final LogRepo logRepo;

    public PerformanceChecker(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    @Pointcut("execution(* com.ppv.notifier.service.NotificationService.send(..))")
    public void checkPerformance() {
    }

    @Around(value = "checkPerformance()")
    public void check(ProceedingJoinPoint joinPoint) {
        try {
            LocalDateTime startTime = LocalDateTime.now();

            joinPoint.proceed();

            logRepo.save(LogInfo.builder()
                    .type(LogType.PERFORMANCE)
                    .message(String.format(MSG_TEMPLATE,
                            ChronoUnit.SECONDS.between(startTime, LocalDateTime.now())))
                    .build());
        } catch (Throwable throwable) {
            //do nothing
        }
    }

}
