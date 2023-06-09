package com.smw.spring.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import com.smw.spring.beans.factory.FactoryBean;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.beans.factory.config.ConfigurableBeanFactory;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements
    ConfigurableBeanFactory {

  private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

  private final ClassLoader beanClassLoader = ClassUtil.getClassLoader();


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

  public ClassLoader getBeanClassLoader() {
    return this.beanClassLoader;
  }

  private <T> T doGetBean(String beanName, Object... args) {
    Object sharedInstance = getSingleton(beanName);
    if (sharedInstance != null) {
      return (T) getObjectForBeanInstance(sharedInstance, beanName);
    }
    BeanDefinition beanDefinition = getBeanDefinition(beanName);
    Object bean = createBean(beanName, beanDefinition, args);
    return (T) getObjectForBeanInstance(bean, beanName);
  }

  private Object getObjectForBeanInstance(Object bean, String name) {
    if (!(bean instanceof FactoryBean)) {
      return bean;
    }
    Object object = getCacheObjectForFactoryBean(name);
    if (object == null) {
      FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
      object = getObjectFromFactoryBean(factoryBean, name);
    }
    return object;
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
