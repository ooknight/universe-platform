package test.io.github.ooknight.universe.support.secure.api;

import io.github.ooknight.universe.support.secure.api.configure.SecureApiAutoConfiguration;
import io.github.ooknight.universe.support.secure.api.spring.SecureRequestBodyAdvice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = SecureApiAutoConfiguration.class)
public class UnitTest {

    @Resource
    private SecureRequestBodyAdvice advice;

    @Test
    public void test() {
        Assertions.assertNotNull(advice);
    }
}
