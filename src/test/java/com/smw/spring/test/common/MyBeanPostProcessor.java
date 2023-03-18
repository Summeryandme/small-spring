package com.smw.spring.test.common;

import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    if ("userService".equals(beanName)) {
      UserService userService = (UserService) bean;
      userService.setLocation("改为武汉 --- postProcessBeforeInitialization");
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    return bean;
  }
}
