package com.smw.spring.aop.framework.adapter;

import com.smw.spring.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

  private MethodBeforeAdvice methodBeforeAdvice;

  public MethodBeforeAdviceInterceptor() {
  }

  public MethodBeforeAdviceInterceptor(MethodBeforeAdvice methodBeforeAdvice) {
    this.methodBeforeAdvice = methodBeforeAdvice;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    this.methodBeforeAdvice.before(invocation.getMethod(), invocation.getArguments(),
        invocation.getThis());
    return invocation.proceed();
  }
}
