package com.smw.spring.context.event;

import com.smw.spring.beans.BeansException;
import com.smw.spring.beans.factory.BeanFactory;
import com.smw.spring.beans.factory.BeanFactoryAware;
import com.smw.spring.context.ApplicationEvent;
import com.smw.spring.context.ApplicationListener;
import com.smw.spring.util.ClassUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster,
    BeanFactoryAware {

  public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

  private BeanFactory beanFactory;

  @Override
  public void addApplicationListener(ApplicationListener<?> applicationListener) {
    applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
  }

  @Override
  public void removeApplicationListener(ApplicationListener<?> applicationListener) {
    applicationListeners.remove(applicationListener);
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
    LinkedList<ApplicationListener> allListeners = new LinkedList<>();
    for (ApplicationListener listener : applicationListeners) {
      if (supportsEvent(listener, event)) {
        allListeners.add(listener);
      }
    }
    return allListeners;
  }

  protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener,
      ApplicationEvent event) {
    Class<? extends ApplicationListener> listenerClass = listener.getClass();
    Class<?> targetClass =
        ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
    Type genericInterface = targetClass.getGenericInterfaces()[0];
    Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
    String className = actualTypeArgument.getTypeName();
    Class<?> eventClassName;
    try {
      eventClassName = Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new BeansException("wrong event class name: ", e);
    }
    return eventClassName.isAssignableFrom(event.getClass());
  }
}
