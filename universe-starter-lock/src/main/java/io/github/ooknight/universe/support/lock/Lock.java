package io.github.ooknight.universe.support.lock;

public interface Lock {

    boolean acquire();

    void release();
}
