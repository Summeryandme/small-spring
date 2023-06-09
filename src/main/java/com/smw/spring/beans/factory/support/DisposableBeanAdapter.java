package com.smw.spring.beans.factory.support;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.DisposableBean;
import com.smw.spring.beans.factory.config.BeanDefinition;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

  private final Object bean;
  private final String beanName;

  private final String destroyMethodName;

  public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
    this.bean = bean;
    this.beanName = beanName;
    this.destroyMethodName = beanDefinition.getDestroyMethodName();
  }

  @Override
  public void destroy() throws Exception {
    if (bean instanceof DisposableBean) {
      ((DisposableBean) bean).destroy();
    }

    if (CharSequenceUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean
        && "destroy".equals(this.destroyMethodName))) {
      Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
      destroyMethod.invoke(bean);
    }
  }
}
