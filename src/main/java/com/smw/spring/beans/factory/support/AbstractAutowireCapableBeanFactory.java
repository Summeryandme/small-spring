package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition) {
    Object bean;
    try {
      bean = beanDefinition.getBeanClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new BeansException("instantiation of bean failed", e);
    }
    addSingleton(beanName, bean);
    return bean;
  }
}
