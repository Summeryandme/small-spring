package com.smw.spring.aop.framework.autoproxy;

import com.smw.spring.aop.AdvisedSupport;
import com.smw.spring.aop.Advisor;
import com.smw.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.smw.spring.aop.ClassFilter;
import com.smw.spring.aop.Pointcut;
import com.smw.spring.aop.TargetSource;
import com.smw.spring.aop.framework.ProxyFactory;
import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.BeanFactory;
import com.smw.spring.beans.factory.BeanFactoryAware;
import com.smw.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.smw.spring.beans.factory.support.DefaultListableBeanFactory;
import java.util.Collection;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor,
    BeanFactoryAware {

  private DefaultListableBeanFactory beanFactory;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = (DefaultListableBeanFactory) beanFactory;
  }

  @Override
  public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
      throws BeansException {

    if (isInfrastructureClass(beanClass)) {
      return null;
    }

    Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(
        AspectJExpressionPointcutAdvisor.class).values();

    for (AspectJExpressionPointcutAdvisor advisor : advisors) {
      ClassFilter classFilter = advisor.getPointcut().getClassFilter();
      if (!classFilter.matches(beanClass)) {
        continue;
      }

      AdvisedSupport advisedSupport = new AdvisedSupport();

      TargetSource targetSource = null;
      try {
        targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
      } catch (Exception e) {
        e.printStackTrace();
      }
      advisedSupport.setTargetSource(targetSource);
      advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
      advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
      advisedSupport.setProxyTargetClass(false);

      return new ProxyFactory(advisedSupport).getProxy();
    }
    return null;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    return bean;
  }

  private boolean isInfrastructureClass(Class<?> beanClass) {
    return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
        || Advisor.class.isAssignableFrom(beanClass);
  }
}
