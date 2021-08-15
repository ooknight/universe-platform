package io.github.ooknight.universe.core.framework.exception;

public class BusinessException extends RuntimeException {

    //private static final String TEMPLATE = "error.business.%s";
    private final String code;
    private final Object[] args;

    public BusinessException(String code, Object... args) {
        super(code);
        this.code = code;
        this.args = args;
    }

    public BusinessException(String code, Throwable cause, Object... args) {
        super(code, cause);
        this.code = code;
        this.args = args;
    }

    public <T extends Enum<?> & BusinessExceptionType> BusinessException(T code, Object... args) {
        this(String.valueOf(code), args);
    }

    public <T extends Enum<?> & BusinessExceptionType> BusinessException(T code, Throwable cause, Object... args) {
        this(String.valueOf(code), cause, args);
    }

    public String code() {
        return this.code;
    }

    public Object[] args() {
        return this.args;
    }
}
