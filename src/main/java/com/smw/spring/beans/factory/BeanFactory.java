package com.smw.spring.beans.factory;

public interface BeanFactory {

  Object getBean(String beanName);
  Object getBean(String beanName, Object... args);

  <T> T getBean(String name, Class<T> requiredType);
}
