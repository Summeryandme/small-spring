package com.smw.spring.context.event;

import com.smw.spring.context.ApplicationContext;
import com.smw.spring.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {

  public ApplicationContextEvent(Object source) {
    super(source);
  }

  public final ApplicationContext getApplicationContext() {
    return (ApplicationContext) getSource();
  }
}
