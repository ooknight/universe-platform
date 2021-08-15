package io.github.ooknight.universe.core.client.web;

import static io.github.ooknight.universe.support.utils.COMBINE.x;

import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    private static final String KEY_TRACER = "tracer";

    @Override
    protected boolean shouldLog(@NonNull HttpServletRequest request) {
        return logger.isDebugEnabled();
    }

    @Override
    protected void beforeRequest(@NonNull HttpServletRequest request, @NonNull String message) {
        MDC.put(KEY_TRACER, x.serial.uuid());
        logger.trace(message);
    }

    @Override
    protected void afterRequest(@NonNull HttpServletRequest request, @NonNull String message) {
        logger.debug(message);
        MDC.remove(KEY_TRACER);
    }
}
