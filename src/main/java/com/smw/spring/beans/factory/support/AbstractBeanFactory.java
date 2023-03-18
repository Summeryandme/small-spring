package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.beans.factory.config.ConfigurableBeanFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements
    ConfigurableBeanFactory {

  private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

  @Override
  public Object getBean(String beanName) {
    return doGetBean(beanName);
  }

  @Override
  public Object getBean(String beanName, Object... args) {
    return doGetBean(beanName, args);
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) {
    return (T) getBean(name);
  }

  private Object doGetBean(String beanName, Object... args) {
    Object bean = getSingleton(beanName);
    if (bean != null) {
      return bean;
    }
    BeanDefinition beanDefinition = getBeanDefinition(beanName);
    return createBean(beanName, beanDefinition, args);
  }

  protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,
      Object... args);

  protected abstract BeanDefinition getBeanDefinition(String beanName);

  @Override
  public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
    this.beanPostProcessors.remove(beanPostProcessor);
    this.beanPostProcessors.add(beanPostProcessor);
  }

  public List<BeanPostProcessor> getBeanPostProcessors() {
    return this.beanPostProcessors;
  }
}
