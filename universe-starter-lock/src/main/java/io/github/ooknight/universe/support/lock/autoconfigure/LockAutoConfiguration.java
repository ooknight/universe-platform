package io.github.ooknight.universe.support.lock.autoconfigure;

import io.github.ooknight.universe.support.lock.aop.LockAspect;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(LockProperties.class)
@Import(LockAspect.class)
public class LockAutoConfiguration {

    private LockProperties properties;
}
