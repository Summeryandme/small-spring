package com.smw.spring.test.bean;

import com.smw.spring.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

  @Override
  public void before(Method method, Object[] args, Object target) {
    System.out.println("拦截方法：" + method.getName());
  }

}
