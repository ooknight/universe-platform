package io.github.ooknight.universe.support.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;

public class SimpleRedisLock implements Lock {

    private static final Logger log = LoggerFactory.getLogger(SimpleRedisLock.class);

    private static final RedisScript<Boolean> SCRIPT_LOCK = new DefaultRedisScript<>("if(redis.call('SET',KEYS[1], ARGV[1], 'NX', 'EX', ARGV[2])) then return true else return false end", Boolean.class);
    private static final RedisScript<Boolean> SCRIPT_UNLOCK = new DefaultRedisScript<>("if redis.call('GET', KEYS[1]) == ARGV[1] then return redis.call('DEL', KEYS[1]) else return false end", Boolean.class);
    //
    private final StringRedisTemplate redis;
    private final String key;
    private final String mark;
    private final String expire;

    public SimpleRedisLock(StringRedisTemplate redis, String key, long expire) {
        this.redis = redis;
        this.key = String.format("universe.lock.%s", key);
        this.mark = UUID.randomUUID().toString();
        this.expire = String.valueOf(expire);
    }

    @Override
    public boolean acquire() {
        Boolean result = redis.execute(SCRIPT_LOCK, Collections.singletonList(key), mark, expire);
        if (Boolean.TRUE.equals(result)) {
            log.info("获取锁:{}:{}:成功", key, mark);
            return true;
        } else {
            log.info("获取锁:{}:{}:失败", key, mark);
            return false;
        }
    }

    @Override
    public void release() {
        Boolean result = redis.execute(SCRIPT_UNLOCK, Collections.singletonList(key), mark);
        if (Boolean.TRUE.equals(result)) {
            log.info("释放锁:{}:{}:成功", key, mark);
        } else {
            log.warn("释放锁:{}:{}:失败", key, mark);
        }
    }
}
