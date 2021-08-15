package io.github.ooknight.universe.core.client.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;
import java.util.Collections;

@ConfigurationProperties(prefix = "universe.web")
public class WebProperties {

    private Collection<String> loggingUrlPatterns = Collections.emptySet();

    public Collection<String> getLoggingUrlPatterns() {
        return loggingUrlPatterns;
    }

    public void setLoggingUrlPatterns(Collection<String> loggingUrlPatterns) {
        this.loggingUrlPatterns = loggingUrlPatterns;
    }
}
