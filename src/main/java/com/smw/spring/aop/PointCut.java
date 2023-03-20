package com.smw.spring.aop;

public interface PointCut {

  ClassFilter getClassFilter();

  MethodMatcher getMethodMatcher();

}
