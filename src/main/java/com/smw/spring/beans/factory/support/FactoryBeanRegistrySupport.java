package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.FactoryBean;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

  private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

  protected Object getCacheObjectForFactoryBean(String beanName) {
    Object object = this.factoryBeanObjectCache.get(beanName);
    return (object != NULL_OBJECT ? object : null);
  }

  protected <T> T getObjectFromFactoryBean(FactoryBean<T> factoryBean, String beanName) {
    if (factoryBean.isSingleton()) {
      Object object = this.factoryBeanObjectCache.get(beanName);
      if (object == null) {
        object = doGetObjectFromFactoryBean(factoryBean, beanName);
        this.factoryBeanObjectCache.put(beanName, object != null ? object : NULL_OBJECT);
      }
      return (object != NULL_OBJECT ? (T) object : null);
    }
    return doGetObjectFromFactoryBean(factoryBean, beanName);
  }

  private <T> T doGetObjectFromFactoryBean(FactoryBean<T> factoryBean, String name) {
    return factoryBean.getObject();
  }
}
