package io.github.ooknight.universe.support.lock.annotation;

import io.github.ooknight.universe.support.lock.LockType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LockUp {

    String key();

    /*
    String mark() default "0";
    */

    /**
     * 锁类型，默认可重入锁
     *
     * @return 锁类型
     */
    LockType type() default LockType.REENTRANT;

    /**
     * 尝试加锁, 最多等待时间, 单位"秒"
     *
     * @return 等待时间
     */
    long waiting() default 0;

    /**
     * 上锁以后xxx秒自动解锁, 单位"秒"
     *
     * @return 失效时间
     */
    long expire();
}
