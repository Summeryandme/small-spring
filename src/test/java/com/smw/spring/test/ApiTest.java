package com.smw.spring.test;


import com.smw.spring.context.support.ClassPathXmlApplicationContext;
import com.smw.spring.test.event.CustomEvent;
import org.junit.jupiter.api.Test;

class ApiTest {

  @Test
  void test_event() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "classpath:spring.xml");
    applicationContext.publishEvent(
        new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

    applicationContext.registerShutdownHook();
  }

}