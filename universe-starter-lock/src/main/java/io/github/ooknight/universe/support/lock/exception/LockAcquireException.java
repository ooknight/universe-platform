package io.github.ooknight.universe.support.lock.exception;

public class LockAcquireException extends RuntimeException {

    public LockAcquireException(String key) {
        super(String.format("lock acquire fail : %s", key));
    }
}
