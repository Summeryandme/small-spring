package com.smw.spring.context;

public interface ConfigurableApplicationContext extends ApplicationContext{

  void refresh();

  void registerShutdownHook();

  void close();
}
