package io.github.ooknight.universe.support.lock.aop;

import io.github.ooknight.universe.core.prime.constant.AspectOrder;
import io.github.ooknight.universe.support.lock.Lock;
import io.github.ooknight.universe.support.lock.SimpleRedisLock;
import io.github.ooknight.universe.support.lock.annotation.LockUp;
import io.github.ooknight.universe.support.lock.exception.LockAcquireException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;

@Aspect
@Order(AspectOrder.LOCK)
public class LockAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Resource
    private StringRedisTemplate redis;

    @Around(value = "@annotation(e)")
    public Object around(ProceedingJoinPoint point, LockUp e) throws Throwable {
        String key = parse(e.key(), point);
        Lock lock = new SimpleRedisLock(redis, key, e.expire());
        boolean locked = false;
        try {
            locked = lock.acquire();
            if (locked) {
                return point.proceed();
            } else {
                throw new LockAcquireException(e.key());
            }
        } finally {
            if (locked) {
                lock.release();
            }
        }
    }

    private String parse(String spel, JoinPoint point) {
        String[] names = ((MethodSignature) point.getSignature()).getParameterNames();
        Object[] values = point.getArgs();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < names.length; i++) {
            context.setVariable(names[i], values[i]);
        }
        return parser.parseExpression(spel).getValue(context, String.class);
    }
}
