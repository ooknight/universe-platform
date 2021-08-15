package io.github.ooknight.universe.support.lock;

import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class ReadLock implements Lock {

    private RReadWriteLock lock;
    private RedissonClient redis;
    private String key;
    private long wait;
    private long lease;

    public ReadLock(RedissonClient redis, String key, long wait, long lease) {
        this.redis = redis;
        this.key = key;
        this.wait = wait;
        this.lease = lease;
    }

    @Override
    public boolean acquire() {
        try {
            lock = redis.getReadWriteLock(key);
            return lock.readLock().tryLock(wait, lease, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void release() {
        if (lock.readLock().isHeldByCurrentThread()) {
            lock.readLock().unlockAsync();
        }
    }
}
