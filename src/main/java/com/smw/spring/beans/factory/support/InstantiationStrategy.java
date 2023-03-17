package com.smw.spring.beans.factory.support;

import com.smw.spring.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

  Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor,
      Object... args);

}
