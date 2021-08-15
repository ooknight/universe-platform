package test;

import io.github.ooknight.universe.platform.testing.JUNIT;
import static io.github.ooknight.universe.support.utils.COMBINE.U.console;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

public class Demo {

    @Test
    public void test1() {
        console.echo(1);
    }

    @Test
    public void test2() {
        console.echo(Lists.newArrayList(1, 2, 3));
    }

    @Test
    public void test3() {
        console.echo(JUNIT.PROFILE_DEVELOP);
    }
}
