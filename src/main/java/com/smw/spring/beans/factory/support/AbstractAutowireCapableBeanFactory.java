package com.smw.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.PropertyValue;
import com.smw.spring.beans.PropertyValues;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanReference;
import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

  private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

  @Override
  protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
    Object bean = createBeanInstance(beanDefinition, beanName, args);
    applyPropertyInstance(beanName, bean, beanDefinition);
    addSingleton(beanName, bean);
    return bean;
  }

  private Object createBeanInstance(BeanDefinition beanDefinition, String beanName,
      Object... args) {
    Constructor<?> constructor = null;
    Class<?> beanClass = beanDefinition.getBeanClass();
    Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
    for (Constructor<?> declaredConstructor : declaredConstructors) {
      if (args != null && checkParameterType(declaredConstructor.getParameterTypes(), args)) {
        constructor = declaredConstructor;
        break;
      }
    }
    return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
  }

  private void applyPropertyInstance(String beanName, Object bean, BeanDefinition beanDefinition) {
    try {
      PropertyValues propertyValues = beanDefinition.getPropertyValues();
      for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
        String name = propertyValue.getName();
        Object value = propertyValue.getValue();
        if (value instanceof BeanReference) {
          BeanReference beanReference = (BeanReference) value;
          value = getBean(beanReference.getBeanName());
        }
        BeanUtil.setFieldValue(bean, name, value);
      }
    } catch (Exception e) {
      throw new BeansException("Error setting property value: " + beanName, e);
    }

  }

  private boolean checkParameterType(Class<?>[] parameterTypes, Object[] args) {
    if (parameterTypes.length != args.length) {
      return false;
    }
    boolean isSameType = true;
    for (int i = 0; i < args.length; i++) {
      if (parameterTypes[i] != args[i].getClass()) {
        isSameType = false;
        break;
      }
    }
    return isSameType;
  }

  public InstantiationStrategy getInstantiationStrategy() {
    return instantiationStrategy;
  }

  public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
    this.instantiationStrategy = instantiationStrategy;
  }


}
