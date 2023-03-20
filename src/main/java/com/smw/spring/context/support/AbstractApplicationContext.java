package com.smw.spring.context.support;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.ConfigurableListableBeanFactory;
import com.smw.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.smw.spring.beans.factory.config.BeanPostProcessor;
import com.smw.spring.context.ApplicationEvent;
import com.smw.spring.context.ApplicationListener;
import com.smw.spring.context.ConfigurableApplicationContext;
import com.smw.spring.context.event.ApplicationEventMulticaster;
import com.smw.spring.context.event.ContextClosedEvent;
import com.smw.spring.context.event.ContextRefreshedEvent;
import com.smw.spring.context.event.SimpleApplicationEventMulticaster;
import com.smw.spring.core.io.DefaultResourceLoader;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

  public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

  private ApplicationEventMulticaster applicationEventMulticaster;

  @Override
  public void refresh() {
    // 1 创建 BeanFactory，并加载 BeanDefinition
    refreshBeanFactory();

    // 2 获取 BeanFactory
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();

    // 3.添加 ApplicationContextAwareProcessor，让继承 ApplicationContextAware 接口的 bean 对象可以感知所属的 ApplicationContext
    beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

    // 4 在 bean 实例化之前，执行 BeanFactoryPostProcessor
    invokeBeanFactoryPostProcessors(beanFactory);

    // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
    registerBeanPostProcessors(beanFactory);

    // 6. 初始化事件发布者
    initApplicationEventMulticaster();

    // 7. 注册事件监听器
    registerListeners();

    // 8. 提前实例化单例Bean对象
    beanFactory.preInstantiateSingletons();

    // 9. 发布容器刷新完成事件
    finishRefresh();

  }

  private void finishRefresh() {
    publishEvent(new ContextRefreshedEvent(this));
  }

  @Override
  public void publishEvent(ApplicationEvent event) {
    applicationEventMulticaster.multicastEvent(event);
  }

  private void registerListeners() {
    Collection<ApplicationListener> applicationListeners = getBeansOfType(
        ApplicationListener.class).values();
    for (ApplicationListener applicationListener : applicationListeners) {
      applicationEventMulticaster.addApplicationListener(applicationListener);
    }
  }

  private void initApplicationEventMulticaster() {
    ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
        applicationEventMulticaster);
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
    publishEvent(new ContextClosedEvent(this));
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
