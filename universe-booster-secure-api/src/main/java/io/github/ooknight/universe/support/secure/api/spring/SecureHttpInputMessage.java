package io.github.ooknight.universe.support.secure.api.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.InputStream;

@SuppressWarnings("NullableProblems")
public class SecureHttpInputMessage implements HttpInputMessage {

    private final InputStream body;
    private final HttpHeaders httpHeaders;

    public SecureHttpInputMessage(InputStream body, HttpHeaders httpHeaders) {
        this.body = body;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public InputStream getBody() {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}
