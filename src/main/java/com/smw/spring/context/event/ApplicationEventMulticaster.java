package com.smw.spring.context.event;


import com.smw.spring.context.ApplicationEvent;
import com.smw.spring.context.ApplicationListener;

public interface ApplicationEventMulticaster {

  void addApplicationListener(ApplicationListener<?> applicationListener);

  void removeApplicationListener(ApplicationListener<?> applicationListener);

  void multicastEvent(ApplicationEvent event);
}
