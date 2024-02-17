package com.taxah.spring_dz5.aspects;


import com.taxah.spring_dz5.service.FileGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



/**
 * TrackUserAspect Class
 * <p>
 * This class implements an aspect in Spring framework to track user actions.
 * It is annotated with @Aspect to indicate that it is an aspect.
 * <p>
 * Usage:
 * - Annotate methods that need to be tracked with @TrackUserAction from com.taxah.spring_dz5.aspects package.
 * - The aspect will log the execution of methods annotated with @TrackUserAction after their execution.
 * <p>
 * The above method execution will be logged by TrackUserAspect after its execution.
 * <p>
 * Methods:
 * - logExecution: A method annotated with @After and listens for methods annotated with @TrackUserAction.
 * It logs the execution of such methods after their execution.
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class TrackUserAspect {

    private final FileGateway fileGateway;

    /**
     * logExecution Method
     * <p>
     * Listens for methods annotated with @TrackUserAction and logs their execution.
     *
     * @param joinPoint The join point representing the intercepted method execution.
     * @throws Throwable If an error occurs during execution.
     */
    @After("@annotation(com.taxah.spring_dz5.aspects.TrackUserAction)")
    public void logExecution(JoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        fileGateway.writeToFile("UserRequestLog.txt", method);
        log.info("User action: " + method + " executed!");
    }
}
