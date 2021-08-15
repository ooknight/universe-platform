package test.io.github.ooknight.universe.support.trace;

import io.github.ooknight.universe.support.trace.annotation.Tracing;
import io.github.ooknight.universe.support.trace.autoconfigure.TraceAutoConfiguration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TraceTest.class)
@SpringBootConfiguration
@ImportAutoConfiguration({TraceAutoConfiguration.class, AopAutoConfiguration.class})
public class TraceTest {

    @Resource
    private TestService service;

    @Test
    public void aop1() {
        service.test1(10);
        service.test2(10);
    }

    @Test
    public void aop2() {
        service.test1(20);
        service.test2(20);
    }

    @Service
    public static class TestService {

        @Tracing(topic = "'key:'+#element", action = "测试")
        void test1(int element) {
            if (element == 20) {
                throw new RuntimeException("测试异常");
            } else {
                System.out.println("...");
            }
        }

        @SuppressWarnings("UnusedReturnValue")
        @Tracing(topic = "'key:'+#returning", action = "测试")
        int test2(int element) {
            if (element == 20) {
                throw new RuntimeException("测试异常");
            } else {
                System.out.println("...");
                return 10 + element;
            }
        }
    }
}
