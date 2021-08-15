package io.github.ooknight.universe.core.prototype.event;

import static io.github.ooknight.universe.support.utils.COMBINE.x;

import org.springframework.context.ApplicationEvent;

public abstract class BusinessEvent extends ApplicationEvent {

    public BusinessEvent() {
        super(1);
    }

    @Override
    public String toString() {
        return x.json.string(this);
    }
}
