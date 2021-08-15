package test.io.github.ooknight.universe.support.trace;

import io.github.ooknight.universe.support.trace.Tracer;
import io.github.ooknight.universe.support.trace.annotation.Tracing;
import io.github.ooknight.universe.support.trace.autoconfigure.TraceAutoConfiguration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TraceWithTracerTest.class)
@SpringBootConfiguration
@ImportAutoConfiguration({TraceAutoConfiguration.class, AopAutoConfiguration.class})
public class TraceWithTracerTest {

    @Resource
    private TestService service;

    @Test
    public void aop1() {
        service.test(1);
    }

    @Test
    public void aop2() {
        service.test(2);
    }

    @Component
    public static class TestTracer implements Tracer {

        private static final Logger log = LoggerFactory.getLogger(TestTracer.class);

        @Override
        public void execute(String topic, String action) {
            log.info("UNIVERSE-TRACING-WITH-TRACER : {} : {}", topic, action);
        }
    }

    @Service
    public static class TestService {

        @Tracing(topic = "'key:'+#element", action = "测试")
        void test(int element) {
            if (element == 2) {
                throw new RuntimeException("测试异常");
            } else {
                System.out.println("...");
            }
        }
    }
}
