package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

  void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
