package com.smw.spring.beans.factory.config;

import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);

}
