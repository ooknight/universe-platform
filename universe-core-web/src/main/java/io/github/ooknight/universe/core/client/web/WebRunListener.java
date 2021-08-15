package io.github.ooknight.universe.core.client.web;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.Ordered;

import java.util.Properties;

public class WebRunListener implements SpringApplicationRunListener, Ordered {

    private final SpringApplication application;

    @SuppressWarnings("unused")
    public WebRunListener(SpringApplication application, String[] args) {
        this.application = application;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void starting(ConfigurableBootstrapContext context) {
        Properties p = new Properties();
        p.setProperty("spring.main.banner-mode", "off");
        p.setProperty("logging.pattern.console", "%d{HH:mm:ss.SSS} %highlight([%.-1level] %msg) %white(@ %logger) %n");
        p.setProperty("server.compression.enabled", "true");
        p.setProperty("server.compression.mime-types", "application/json,application/javascript,text/css");
        p.setProperty("spring.data.web.pageable.one-indexed-parameters", "true");
        application.setDefaultProperties(p);
        //application.setBannerMode(Banner.Mode.OFF);
    }
}
