package com.smw.spring.aop;

public interface PointcutAdvisor extends Advisor {

  Pointcut getPointcut();

}
