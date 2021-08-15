package io.github.ooknight.universe.core.prototype.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

public class BusinessEventPublisher implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(BusinessEventPublisher.class);
    //
    private ApplicationContext context;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void publish(BusinessEvent event) {
        context.publishEvent(event);
        logger.debug("business event publish success {}", event);
    }

    /*
    //@ExceptionHandler(RuntimeException.class)
    //public void foo() {
    //    logger.error("business event publish error");
    //}
    */
}
