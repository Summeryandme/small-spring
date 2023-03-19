package com.smw.spring.test;


import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;
import com.smw.spring.beans.factory.support.XmlBeanDefinitionReader;
import com.smw.spring.context.support.ClassPathXmlApplicationContext;
import com.smw.spring.test.bean.UserService;
import com.smw.spring.test.common.MyBeanFactoryPostProcessor;
import com.smw.spring.test.common.MyBeanPostProcessor;
import org.junit.jupiter.api.Test;

class ApiTest {

  @Test
  void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
    // 1. 初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. 读取配置文件&注册Bean
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
    reader.loadResourceDefinition("classpath:spring.xml");

    // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
    MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

    // 4. Bean实例化之后，修改 Bean 属性信息
    MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
    beanFactory.addBeanPostProcessor(beanPostProcessor);

    // 5. 获取Bean对象调用方法
    UserService userService = beanFactory.getBean("userService", UserService.class);
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
  }

  @Test
  void test_xml() {
    // 1. 初始化 BeanFactory
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:spring.xml");
    applicationContext.registerShutdownHook();

    // 2. 获取Bean对象调用方法
    UserService userService = applicationContext.getBean("userService", UserService.class);
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
  }
}