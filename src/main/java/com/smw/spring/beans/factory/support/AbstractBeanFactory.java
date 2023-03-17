package com.smw.spring.beans.factory.support;

import com.smw.spring.BeanFactory;
import com.smw.spring.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements
    BeanFactory {

  @Override
  public Object getBean(String beanName) {
    Object bean = getSingleton(beanName);
    if (bean != null) {
      return bean;
    }
    BeanDefinition beanDefinition = getBeanDefinition(beanName);
    return createBean(beanName, beanDefinition);
  }

  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
  protected abstract BeanDefinition getBeanDefinition(String beanName);
}
