package io.github.ooknight.universe.core.framework.exception;

import java.util.Map;

public class RemoteException extends RuntimeException {

    private final String remote;
    private final Map<?, ?> response;

    public RemoteException(String remote, Map<?, ?> response) {
        this.remote = remote;
        this.response = response;
    }

    public String remote() {
        return remote;
    }

    public Map<?, ?> response() {
        return response;
    }

    @Override
    public String getMessage() {
        return "远程调用异常:" + "[" + remote + "][" + response + "]";
    }
}
