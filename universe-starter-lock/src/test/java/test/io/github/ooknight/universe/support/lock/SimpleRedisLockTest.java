package test.io.github.ooknight.universe.support.lock;

import io.github.ooknight.universe.support.lock.SimpleRedisLock;
import io.github.ooknight.universe.support.lock.annotation.LockUp;
import io.github.ooknight.universe.support.lock.autoconfigure.LockAutoConfiguration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBootConfiguration
@ImportAutoConfiguration({RedisAutoConfiguration.class, LockAutoConfiguration.class, AopAutoConfiguration.class})
public class SimpleRedisLockTest {

    @Resource
    private StringRedisTemplate redis;
    @Resource
    private TestService s1;
    @Resource
    private TestReentrantService s2;

    @Test
    public void test1() {
        SimpleRedisLock lock = new SimpleRedisLock(redis, "test", 5L);
        try {
            lock.acquire();
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }
    }

    @Test
    public void test2() {
        SimpleRedisLock lock = new SimpleRedisLock(redis, "test", 5L);
        try {
            lock.acquire();
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }
    }

    @Test
    public void aop() {
        s1.test("test", 1000L);
        s1.test("test", 10000L);
    }

    @Test
    public void reentrant() {
        s2.test("test2", 1000L);
        s2.test("test3", 1000L);
    }

    @Service
    public static class TestService {

        @LockUp(key = "'key.'+#element", expire = 5)
        void test(String element, long x) {
            try {
                System.out.println("this is " + element);
                Thread.sleep(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Service
    public static class TestReentrantService {

        @Resource
        private TestService service;

        @LockUp(key = "biz", expire = 5)
        void test(String element, long x) {
            service.test(element, x);
        }
    }
}
