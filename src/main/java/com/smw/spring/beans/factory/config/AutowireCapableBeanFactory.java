package com.smw.spring.beans.factory.config;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

  Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
      throws BeansException;

  Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
      throws BeansException;

}