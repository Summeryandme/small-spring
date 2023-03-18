package com.smw.spring.context.support;

import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;
import com.smw.spring.beans.factory.support.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

  @Override
  protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
    XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,
        this);
    String[] configLocations = getConfigLocations();
    if (null != configLocations) {
      beanDefinitionReader.loadResourceDefinition(configLocations);
    }
  }

  protected abstract String[] getConfigLocations();
}
