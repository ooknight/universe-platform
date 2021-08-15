package io.github.ooknight.universe.core.kernel;

import io.github.ooknight.universe.core.prime.constant.AspectOrder;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@EnableRetry
@EnableScheduling
@EnableCaching(order = AspectOrder.CACHE)
@EnableTransactionManagement(order = AspectOrder.TRANSACTION)
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class KernelConfiguration {

    /*
    @Bean
    public BusinessEventPublisher businessEventPublisher() {
        return new BusinessEventPublisher();
    }
    */

    /*
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
    */
}
