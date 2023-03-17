package com.smw.spring.test;


import com.smw.spring.beans.PropertyValue;
import com.smw.spring.beans.PropertyValues;
import com.smw.spring.beans.factory.config.BeanDefinition;
import com.smw.spring.beans.factory.config.BeanReference;
import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;
import com.smw.spring.test.bean.UserDao;
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

  @Test
  void test_property() {
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("uId", "10002"));
    propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
    BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
    beanFactory.registerBeanDefinition("userService", beanDefinition);
    UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
  }
}