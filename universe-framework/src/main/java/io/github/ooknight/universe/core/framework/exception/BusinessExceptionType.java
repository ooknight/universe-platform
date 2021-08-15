package io.github.ooknight.universe.core.framework.exception;

public interface BusinessExceptionType {

    enum Default implements BusinessExceptionType {
        ENTITY_NOT_FOUND, UNAUTHORIZED_ACCESS, SERVICE_NOT_SUPPORT
    }
}
