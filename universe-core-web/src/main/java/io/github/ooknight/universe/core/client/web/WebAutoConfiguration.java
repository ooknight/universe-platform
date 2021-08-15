package io.github.ooknight.universe.core.client.web;

import io.github.ooknight.universe.core.prototype.event.BusinessEventPublisher;
import io.github.ooknight.universe.support.utils.DTF;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.Resource;

@SpringBootConfiguration
@EnableConfigurationProperties(WebProperties.class)
@EnableSpringDataWebSupport
@Import({StatusController.class, ResponseWrapBodyAdvice.class})
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class WebAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private WebProperties web;

    /*
     * setUseTrailingSlashMatch : 设置是否匹配路径模式, 如 '/demo' 是否匹配 '/user/', 默认真即匹配
     * setUseSuffixPatternMatch : 设置是否匹配后缀模式, 如 '/demo' 是否匹配 '/user.*', 默认真即匹配
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(false);
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        DateTimeFormatterRegistrar jsr310 = new DateTimeFormatterRegistrar();
        jsr310.setDateTimeFormatter(DTF.DATE_TIME_DEFAULT);
        jsr310.setDateFormatter(DTF.DATE_DEFAULT);
        jsr310.setTimeFormatter(DTF.TIME_DEFAULT);
        jsr310.registerFormatters(registry);
    }

    @Bean
    @ConditionalOnProperty(name = "universe.web.logging-url-patterns")
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilterRegistrationBean() {
        RequestLoggingFilter filter = new RequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setBeforeMessagePrefix(">>> [");
        filter.setBeforeMessageSuffix("]");
        filter.setAfterMessagePrefix("--- [");
        filter.setAfterMessageSuffix("]");
        FilterRegistrationBean<RequestLoggingFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.setUrlPatterns(web.getLoggingUrlPatterns());
        return bean;
    }

    @Bean
    public BusinessEventPublisher businessEventPublisher() {
        return new BusinessEventPublisher();
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
