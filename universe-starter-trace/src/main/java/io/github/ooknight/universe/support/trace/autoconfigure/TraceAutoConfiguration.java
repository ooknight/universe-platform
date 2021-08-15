package io.github.ooknight.universe.support.trace.autoconfigure;

import io.github.ooknight.universe.support.trace.LoggingTracer;
import io.github.ooknight.universe.support.trace.Tracer;
import io.github.ooknight.universe.support.trace.aop.TraceAspect;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(TraceProperties.class)
@Import(TraceAspect.class)
public class TraceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Tracer.class)
    public Tracer tracer() {
        return new LoggingTracer();
    }
}
