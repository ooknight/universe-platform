package io.github.ooknight.universe.support.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class FairLock implements Lock {

    private RLock lock;
    private RedissonClient redis;
    private String key;
    private long wait;
    private long lease;

    public FairLock(RedissonClient redis, String key, long wait, long lease) {
        this.redis = redis;
        this.key = key;
        this.wait = wait;
        this.lease = lease;
    }

    @Override
    public boolean acquire() {
        try {
            lock = redis.getFairLock(key);
            return lock.tryLock(wait, lease, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void release() {
        if (lock.isHeldByCurrentThread()) {
            lock.unlockAsync();
        }
    }
}
