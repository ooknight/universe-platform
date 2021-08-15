package test.zz;

import io.github.ooknight.universe.core.prototype.authority.InteriorUserType;
import io.github.ooknight.universe.core.prototype.authority.Menu;
import io.github.ooknight.universe.core.prototype.authority.Scope;
import io.github.ooknight.universe.core.prototype.authority.UserState;
import io.github.ooknight.universe.core.prototype.authority.spring.SessionUserAdapter;
import io.github.ooknight.universe.core.prototype.authority.spring.SessionUserExterior;
import io.github.ooknight.universe.core.prototype.authority.spring.SessionUserInterior;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class Scattered {

    @Test
    void testMenu() {
        System.out.println(new Menu("demo:create", "demo-create"));
        System.out.println(new Menu("demo:create", "demo-create", "/demo/create", "demo-icon"));
    }

    @Test
    void testScope() {
        System.out.println(Scope.BUILD(1L, 1L, 1L, true));
        System.out.println(Scope.BUILD(1L, 1L, 1L, false));
        System.out.println(Scope.DUMMY());
    }

    @Test
    void testSessionUser() throws Exception {
        SessionUserAdapter sua = new SessionUserAdapter(1L, 1L, 1L, "test", "", InteriorUserType.NORMAL, 1);
        String x = new ObjectMapper().writeValueAsString(sua);
        System.out.println(x);
    }

    @Test
    void testSessionUserExterior() throws Exception {
        SessionUserExterior user = new SessionUserExterior(1L, "test", "123456", UserState.UNLOCK, true);
        String x = new ObjectMapper().writeValueAsString(user);
        System.out.println(x);
    }

    @Test
    void testSessionUserInterior() throws Exception {
        SessionUserInterior user = new SessionUserInterior(1L, 1L, 1L, "test", "123456", UserState.UNLOCK, true);
        String x = new ObjectMapper().writeValueAsString(user);
        System.out.println(x);
    }
}
