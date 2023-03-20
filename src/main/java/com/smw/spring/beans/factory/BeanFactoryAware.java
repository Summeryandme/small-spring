package com.smw.spring.beans.factory;

public interface BeanFactoryAware extends Aware {

  void setBeanFactory(BeanFactory beanFactory);

}
