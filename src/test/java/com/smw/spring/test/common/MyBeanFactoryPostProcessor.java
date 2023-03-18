package com.smw.spring.test.common;

import com.smw.spring.beans.PropertyValue;
import com.smw.spring.beans.PropertyValues;
import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    propertyValues.addPropertyValue(
        new PropertyValue("company", "改为腾讯 --- postProcessBeanFactory"));
  }
}
