package com.smw.spring.aop.framework;

import com.smw.spring.aop.AdvisedSupport;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.aopalliance.intercept.MethodInterceptor;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

  private final AdvisedSupport advisedSupport;

  public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
    this.advisedSupport = advisedSupport;
  }

  @Override
  public Object getProxy() {
    return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        advisedSupport.getTargetSource()
            .getTargetClass(), this);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (advisedSupport.getMethodMatcher()
        .matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
      MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
      return methodInterceptor.invoke(
          new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method,
              args));
    }
    return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
  }
}
