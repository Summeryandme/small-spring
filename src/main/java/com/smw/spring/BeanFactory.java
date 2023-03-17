package com.smw.spring;

public interface BeanFactory {

  Object getBean(String beanName);
  Object getBean(String beanName, Object... args);

}
