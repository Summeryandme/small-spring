package com.smw.spring.beans.factory;

public interface BeanClassLoaderAware extends Aware {

  void setBeanClassLoader(ClassLoader classLoader);

}
