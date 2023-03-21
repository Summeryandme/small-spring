package com.smw.spring.beans.factory.config;


import com.smw.spring.beans.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

  Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
