package com.haulmont.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_CARRIER")
@Entity(name = "st_Carrier")
public class Carrier {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false, unique = true)
    @NotNull
    private String name;

    @JoinTable(name = "ST_CARRIER_SPACEPORT_LINK",
            joinColumns = @JoinColumn(name = "CARRIER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPACEPORT_ID"))
    @ManyToMany
    private List<Spaceport> spaceports;

    public List<Spaceport> getSpaceports() {
        return spaceports;
    }

    public void setSpaceports(List<Spaceport> spaceports) {
        this.spaceports = spaceports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
