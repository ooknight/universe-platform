package io.github.ooknight.universe.core.domain;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class REntity extends PEntity {

    @WhenCreated
    @Column(name = "created_", updatable = false)
    private LocalDateTime created;

    @WhenModified
    @Column(name = "updated_")
    private LocalDateTime updated;

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}
