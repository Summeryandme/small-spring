package com.smw.spring.context;

import com.smw.spring.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {

  void setApplicationContext(ApplicationContext applicationContext);
}
