package com.ascertia.userserviceclient.aop;


import com.ascertia.userserviceclient.entities.Operations;
import com.ascertia.userserviceclient.repository.OperationsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class Logging {

    @Autowired
    OperationsRepository operationsRepository;

    @Pointcut("within(com.ascertia.userserviceclient.controller.*)")
    public void pointCutController(){

    }


    @AfterThrowing(value = "pointCutController()",throwing = "e")
    public void AfterExceptionOcurred(JoinPoint joinPoint, Exception e){
        log.info("Excetion occured {}",e.getMessage());
    }


    @Around("pointCutController()")
    public Object logCrudOperations(ProceedingJoinPoint joinPoint) throws Throwable {
        Operations operations=new Operations();
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            ObjectMapper objectMapper=new ObjectMapper();
            operations.setRequest_date_Time(LocalDateTime.now());
            operations.setRequest(objectMapper.writeValueAsString(joinPoint.getArgs()));
            operations.setEndPoint(request.getRequestURI());
            Object result=joinPoint.proceed();
            operations.setResponse(objectMapper.writeValueAsString(result));
            operations.setStatus("SUCCESS");
            operationsRepository.save(operations);
            return result;
        }
        catch (Exception e){
            operations.setStatus("FAILED");
            operations.setErrorMessage(e.getMessage());
            operationsRepository.save(operations);
            throw new Exception(e.getMessage());
        }
    }
}
