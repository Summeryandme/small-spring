package com.smw.spring.context.event;

public class ContextClosedEvent extends ApplicationContextEvent {

  public ContextClosedEvent(Object source) {
    super(source);
  }
}
