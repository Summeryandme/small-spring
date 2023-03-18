package com.smw.spring.context.support;

import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;
import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends
    AbstractApplicationContext {

  private DefaultListableBeanFactory beanFactory;

  private DefaultListableBeanFactory createBeanFactory() {
    return new DefaultListableBeanFactory();
  }


  @Override
  protected void refreshBeanFactory() {
    DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
    loadBeanDefinition(defaultListableBeanFactory);
    this.beanFactory = defaultListableBeanFactory;
  }

  protected abstract void loadBeanDefinition(DefaultListableBeanFactory defaultListableBeanFactory);

  @Override
  protected ConfigurableListableBeanFactory getBeanFactory() {
    return beanFactory;
  }
}
