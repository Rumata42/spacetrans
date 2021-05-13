package com.haulmont.spacetrans.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_ATMOSPHERIC_GAS")
@Entity(name = "st_AtmosphericGas")
public class AtmosphericGas {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "ATMOSPHERE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Atmosphere atmosphere;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "GAS_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Gas gas;
    @NumberFormat(pattern = "#.##")
    @Column(name = "VOLUME")
    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
