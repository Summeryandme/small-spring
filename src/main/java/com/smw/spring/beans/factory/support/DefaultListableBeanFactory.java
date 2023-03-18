package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;
import com.smw.spring.beans.factory.config.BeanDefinition;
import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements
    BeanDefinitionRegistry, ConfigurableListableBeanFactory {

  private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

  @Override
  public BeanDefinition getBeanDefinition(String beanName) {
    BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null) {
      throw new BeansException("No bean named " + beanName + " is defined");
    }
    return beanDefinition;
  }

  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public boolean containsBeanDefinition(String beanName) {
    return beanDefinitionMap.containsKey(beanName);
  }

  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) {
    Map<String, T> result = new HashMap<>();
    beanDefinitionMap.forEach((beanName, beanDefinition) -> {
      Class beanClass = beanDefinition.getBeanClass();
      if (type.isAssignableFrom(beanClass)) {
        result.put(beanName, (T) getBean(beanName));
      }
    });
    return result;
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return beanDefinitionMap.keySet().toArray(new String[0]);
  }

  @Override
  public void preInstantiateSingletons() {
    beanDefinitionMap.keySet().forEach(this::getBean);
  }
}
