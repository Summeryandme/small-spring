package com.smw.spring.beans.factory;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory,
    AutowireCapableBeanFactory, ConfigurableBeanFactory {

  BeanDefinition getBeanDefinition(String beanName) throws BeansException;

  void preInstantiateSingletons() throws BeansException;

}