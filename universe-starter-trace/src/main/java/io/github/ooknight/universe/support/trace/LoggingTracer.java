package io.github.ooknight.universe.support.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTracer implements Tracer {

    private static final Logger log = LoggerFactory.getLogger("TRACER");

    @Override
    public void execute(String topic, String action) {
        log.info("UNIVERSE-TRACING : {} : {}", topic, action);
    }
}
