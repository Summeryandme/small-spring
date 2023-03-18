package com.smw.spring.beans.factory.support;

import com.smw.spring.core.io.DefaultResourceLoader;
import com.smw.spring.core.io.Resource;
import com.smw.spring.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements
    BeanDefinitionReader {

  private final BeanDefinitionRegistry beanDefinitionRegistry;

  private final ResourceLoader resourceLoader;

  public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
    this(beanDefinitionRegistry, new DefaultResourceLoader());
  }

  public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry,
      ResourceLoader resourceLoader) {
    this.beanDefinitionRegistry = beanDefinitionRegistry;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public BeanDefinitionRegistry getRegistry() {
    return beanDefinitionRegistry;
  }

  @Override
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}
