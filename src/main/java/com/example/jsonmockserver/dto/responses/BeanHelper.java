package com.example.jsonmockserver.dto.responses;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BeanHelper {
    private static ApplicationContext context;

    private BeanHelper(ApplicationContext context) {
        BeanHelper.context = context;
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {

        Assert.state(context != null, "Spring context in the BeanUtil is not been initialized yet!");
        return context.getBean(clazz);
    }
}