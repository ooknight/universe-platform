package io.github.ooknight.universe.core.domain;

import io.github.ooknight.universe.support.utils.Bean;

import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class UEntity implements Bean {

    @Id
    @Column(name = "id", updatable = false)
    private Long id;

    @WhenCreated
    @Column(name = "created_", updatable = false)
    private LocalDateTime created;

    @WhenModified
    @Column(name = "updated_")
    private LocalDateTime updated;

    @SoftDelete
    @Column(name = "deleted_")
    private boolean deleted;

    public Long id() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public boolean active() {
        return !this.deleted;
    }
}
