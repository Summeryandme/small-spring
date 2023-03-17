package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements
    InstantiationStrategy {

  @Override
  public Object instantiate(BeanDefinition beanDefinition, String beanName,
      Constructor<?> constructor, Object... args) {
    Class<?> beanClass = beanDefinition.getBeanClass();
    try {
      if (constructor != null) {
        return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
      } else {
        return beanClass.getDeclaredConstructor().newInstance();
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
             NoSuchMethodException e) {
      throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
    }
  }
}
