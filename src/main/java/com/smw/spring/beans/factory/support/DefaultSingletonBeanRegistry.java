package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  private final Map<String, Object> singletonObjects = new HashMap<>();

  @Override
  public Object getSingleton(String beanName) {
    return singletonObjects.get(beanName);
  }

  protected void addSingleton(String beanName, Object bean) {
    singletonObjects.put(beanName, bean);
  }

}
