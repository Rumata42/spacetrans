package com.haulmont.spacetrans.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_SPACEPORT")
@Entity(name = "st_Spaceport")
public class Spaceport {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false, unique = true)
    @NotNull
    private String name;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "PLANET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "MOON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Moon moon;

    @Column(name = "IS_DEFAULT", nullable = false)
    @NotNull
    private Boolean isDefault = false;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "COORDINATES_LATITUDE")),
            @AttributeOverride(name = "longitude", column = @Column(name = "COORDINATES_LONGITUDE"))
    })
    private Coordinates coordinates;

    @JoinTable(name = "ST_CARRIER_SPACEPORT_LINK",
            joinColumns = @JoinColumn(name = "SPACEPORT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARRIER_ID"))
    @ManyToMany
    private List<Carrier> carriers;

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
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
