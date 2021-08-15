package test.io.github.ooknight.universe.core.domain.entity;

import io.github.ooknight.universe.core.domain.UEntity;

import io.ebean.annotation.Encrypted;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "e_sample")
public class Sample extends UEntity {

    @Column(name = "name_")
    private String name;

    @Encrypted
    @Column(name = "value1_")
    private String value1;

    @Encrypted(dbEncryption = false)
    @Column(name = "value2_")
    private String value2;
}
