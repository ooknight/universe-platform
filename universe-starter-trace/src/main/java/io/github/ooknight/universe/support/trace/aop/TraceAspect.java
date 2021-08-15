package io.github.ooknight.universe.support.trace.aop;

import io.github.ooknight.universe.core.prime.constant.AspectOrder;
import io.github.ooknight.universe.support.trace.Tracer;
import io.github.ooknight.universe.support.trace.annotation.Tracing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.annotation.Resource;

@Aspect
@Order(AspectOrder.TRACE)
public class TraceAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

    @Resource
    private Tracer tracer;

    @AfterReturning(returning = "returning", pointcut = "@annotation(e)")
    public void around(JoinPoint point, Tracing e, Object returning) {
        String topic = parse(e.topic(), point, returning);
        String action = e.action();
        tracer.execute(topic, action);
    }

    private String parse(String spel, JoinPoint point, Object returning) {
        String[] names = ((MethodSignature) point.getSignature()).getParameterNames();
        Object[] values = point.getArgs();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("returning", returning);
        for (int i = 0; i < names.length; i++) {
            context.setVariable(names[i], values[i]);
        }
        return parser.parseExpression(spel).getValue(context, String.class);
    }
}
