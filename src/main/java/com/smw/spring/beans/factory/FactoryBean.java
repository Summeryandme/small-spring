package com.smw.spring.beans.factory;

public interface FactoryBean<T> {
  T getObject();

  Class<?> getObjectType();

  boolean isSingleton();
}
