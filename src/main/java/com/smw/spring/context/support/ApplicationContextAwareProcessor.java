package com.smw.spring.context.support;

import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.context.ApplicationContext;
import com.smw.spring.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

  private final ApplicationContext applicationContext;

  public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    if (bean instanceof ApplicationContextAware) {
      ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    return bean;
  }
}
