package com.smw.spring.beans.factory;

public interface BeanNameAware extends Aware {

  void setBeanName(String name);
}
