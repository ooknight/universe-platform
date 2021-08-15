package test.io.github.ooknight.universe.core.framework.exception;

import io.github.ooknight.universe.core.framework.exception.BusinessException;
import io.github.ooknight.universe.core.framework.exception.BusinessExceptionType;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BusinessExceptionTest {

    @Test
    public void testOnlyKey1() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.ENTITY_NOT_FOUND);
        }, "ENTITY_NOT_FOUND");
    }

    @Test
    public void testOnlyKey2() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.UNAUTHORIZED_ACCESS);
        }, "UNAUTHORIZED_ACCESS");
    }

    @Test
    public void testWithArgs1() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.ENTITY_NOT_FOUND, E.class, 1);
        }, "ENTITY_NOT_FOUND");
    }

    @Test
    public void testWithArgs2() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.UNAUTHORIZED_ACCESS, "test");
        }, "UNAUTHORIZED_ACCESS");
    }

    @Test
    public void testWithStack1() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.ENTITY_NOT_FOUND, E.class, new IOException());
        }, "ENTITY_NOT_FOUND");
        //thrown.expectCause(Matchers.isA(IOException.class));
    }

    @Test
    public void testWithStack2() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.ENTITY_NOT_FOUND, E.class, new IOException("test"));
        }, "ENTITY_NOT_FOUND");
        //thrown.expectCause(Matchers.isA(IOException.class));
    }

    @Test
    public void testWithArgsAndStack1() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.ENTITY_NOT_FOUND, E.class, new IOException("test"), 1);
        }, "ENTITY_NOT_FOUND");
        //thrown.expectCause(Matchers.isA(IOException.class));
    }

    @Test
    public void testWithArgsAndStack2() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(BusinessExceptionType.Default.UNAUTHORIZED_ACCESS, E.class, new IOException("test"), "test");
        }, "UNAUTHORIZED_ACCESS");
        //thrown.expectCause(Matchers.isA(IOException.class));
    }

    @Test
    public void testCustomize() {
        assertThrows(BusinessException.class, () -> {
            throw new BusinessException(X.UNKNOWN);
        }, "UNKNOWN");
    }

    private enum X implements BusinessExceptionType {
        UNKNOWN
    }

    private static class E {}
}
