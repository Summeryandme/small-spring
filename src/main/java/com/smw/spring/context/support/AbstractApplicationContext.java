package com.smw.spring.context.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;
import com.smw.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.context.ConfigurableApplicationContext;
import com.smw.spring.core.io.DefaultResourceLoader;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

  @Override
  public void refresh() {
    // 1 创建 BeanFactory，并加载 BeanDefinition
    refreshBeanFactory();

    // 2 获取 BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();

    // 3.添加 ApplicationContextAwareProcessor，让继承 ApplicationContextAware 接口的 bean 对象可以感知所属的 ApplicationContext
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

    // 3 在 bean 实例化之前，执行 BeanFactoryPostProcessor
    invokeBeanFactoryPostProcessors(beanFactory);

    // 4 BeanPostProcessor 需要提前于其他 bean 对象实例化之前执行注册操作
    registerBeanPostProcessors(beanFactory);

    // 5 提前实例化单例 bean 对象
    beanFactory.preInstantiateSingletons();

  }

  protected abstract void refreshBeanFactory();

  protected abstract ConfigurableListableBeanFactory getBeanFactory();

  private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(
        BeanFactoryPostProcessor.class);
    for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
      beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
    }
  }

  private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
    Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(
        BeanPostProcessor.class);
    for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
      beanFactory.addBeanPostProcessor(beanPostProcessor);
    }
  }

  @Override
  public void registerShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(this::close));
  }

  @Override
  public void close() {
    getBeanFactory().destroySingletons();
  }

  @Override
  public Object getBean(String beanName) {
    return getBeanFactory().getBean(beanName);
  }

  @Override
  public Object getBean(String beanName, Object... args) {
    return getBeanFactory().getBean(beanName, args);
  }

  @Override
  public <T> T getBean(String name, Class<T> requiredType) {
    return getBeanFactory().getBean(name, requiredType);
  }

  @Override
  public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
    return getBeanFactory().getBeansOfType(type);
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return getBeanFactory().getBeanDefinitionNames();
  }

}
