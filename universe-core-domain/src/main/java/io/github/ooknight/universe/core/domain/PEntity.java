package io.github.ooknight.universe.core.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PEntity extends OEntity {

    @Id
    @Column(name = "id_", updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
