package com.smw.spring.beans.factory.config;

public interface SingletonBeanRegistry {
  Object getSingleton(String beanName);

  void destroySingletons();

  void registerSingleton(String beanName, Object singletonObject);

}
