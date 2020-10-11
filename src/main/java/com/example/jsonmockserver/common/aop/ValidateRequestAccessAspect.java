package com.example.jsonmockserver.common.aop;

import com.example.jsonmockserver.common.annotations.ValidateRequestAccess;
import com.example.jsonmockserver.common.exception.InvalidDataException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Order(0)
@Component
public class ValidateRequestAccessAspect {
    private Logger logger = LoggerFactory.getLogger(ValidateRequestAccessAspect.class);

    @Value("${request.authorization.userName}")
    String userNameDBConfig;
    @Value("${request.authorization.password}")
    String passwordConfig;

    @Around("@annotation(setServletRequest)")
    public Object setMDC(ProceedingJoinPoint joinPoint, ValidateRequestAccess setServletRequest) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        String userName = request.getHeader("user_name");

        String password = request.getHeader("password");
        String uri = request.getRequestURI();
        logger.info(": userName: {}, Password: {}", userName, password, uri);

        if (!Objects.equals(userNameDBConfig, userName) || !Objects.equals(passwordConfig, password)) {
            throw new InvalidDataException("Not Authorized fot this request");
        }


        return joinPoint.proceed();
    }


}
