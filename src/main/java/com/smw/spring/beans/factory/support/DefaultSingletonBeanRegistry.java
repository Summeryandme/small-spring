package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.DisposableBean;
import com.smw.spring.beans.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

  protected static final Object NULL_OBJECT = new Object();

  private final Map<String, Object> singletonObjects = new HashMap<>();

  private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

  @Override
  public Object getSingleton(String beanName) {
    return singletonObjects.get(beanName);
  }

  public void registerDisposableBean(String beanName, DisposableBean bean) {
    disposableBeans.put(beanName, bean);
  }

  protected void addSingleton(String beanName, Object bean) {
    singletonObjects.put(beanName, bean);
  }

  @Override
  public void registerSingleton(String beanName, Object singletonObject) {
    singletonObjects.put(beanName, singletonObject);
  }

  public void destroySingletons() {
    String[] disposableBeanNames = this.disposableBeans.keySet().toArray(new String[0]);
    for (String disposableBeanName : disposableBeanNames) {
      DisposableBean disposableBean = disposableBeans.remove(disposableBeanName);
      try {
        disposableBean.destroy();
      } catch (Exception e) {
        throw new BeansException(
            "Destroy method on bean with name '" + disposableBeanName + "' threw an exception", e);
      }
    }
  }

}
