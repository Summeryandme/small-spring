package com.smw.spring.test;


import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;
import com.smw.spring.test.bean.UserService;
import org.junit.jupiter.api.Test;

class ApiTest {

  @Test
  void test_BeanFactory() {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    beanFactory.registerBeanDefinition("userService", beanDefinition);
    UserService userService = (UserService) beanFactory.getBean("userService", "smw", 26);
    userService.queryUserInfo();
    UserService userService2 = (UserService) beanFactory.getBean("userService");
    userService2.queryUserInfo(); // 第二次从内存中获取
  }


}