package test.io.github.ooknight.universe.core.client.web.security.access;

import io.github.ooknight.universe.core.client.web.security.access.Permission;

import org.junit.jupiter.api.Test;

public class PermissionTest {

    @Test
    public void test() {
        System.out.println(Permission.READ);
        System.out.println(Permission.WRITE);
        System.out.println(Permission.DELETE);
    }
}
