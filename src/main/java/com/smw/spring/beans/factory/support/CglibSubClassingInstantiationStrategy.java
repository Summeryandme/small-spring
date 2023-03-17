package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class CglibSubClassingInstantiationStrategy implements
    InstantiationStrategy {

  @Override
  public Object instantiate(BeanDefinition beanDefinition, String beanName,
      Constructor<?> constructor, Object... args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(beanDefinition.getBeanClass());
    enhancer.setCallback(new NoOp() {
      @Override
      public int hashCode() {
        return super.hashCode();
      }

      @Override
      public boolean equals(Object obj) {
        return super.equals(obj);
      }
    });
    if (constructor == null) {
      return enhancer.create();
    }
    return enhancer.create(constructor.getParameterTypes(), args);
  }
}
