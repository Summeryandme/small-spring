package com.smw.spring.context;

import com.smw.spring.beans.factory.HierarchicalBeanFactory;
import com.smw.spring.beans.factory.ListableBeanFactory;
import com.smw.spring.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,
    ResourceLoader, ApplicationEventPublisher {

}
