package com.smw.spring.aop;

public interface Pointcut {

  ClassFilter getClassFilter();

  MethodMatcher getMethodMatcher();

}
