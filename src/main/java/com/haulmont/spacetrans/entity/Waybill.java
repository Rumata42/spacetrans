package com.haulmont.spacetrans.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_WAYBILL")
@Entity(name = "st_Waybill")
public class Waybill {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "SHIPPER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer shipper;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CONSIGNEE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer consignee;

    @Column(name = "REFERENCE")
    private String reference;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CREATOR_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User creator;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "DEPARTURE_PORT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Spaceport departurePort;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "DESTINATION_PORT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Spaceport destinationPort;

    @Composition
    @OneToMany(mappedBy = "waybill")
    private List<WaybillItem> items;

    @PositiveOrZero
    @Column(name = "TOTAL_WEIGHT")
    private Double totalWeight;

    @PositiveOrZero
    @Column(name = "TOTAL_CHARGE")
    private Double totalCharge;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "CARRIER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Carrier carrier;

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Customer getConsignee() {
        return consignee;
    }

    public void setConsignee(Customer consignee) {
        this.consignee = consignee;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public List<WaybillItem> getItems() {
        return items;
    }

    public void setItems(List<WaybillItem> items) {
        this.items = items;
    }

    public Spaceport getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Spaceport destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Spaceport getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Spaceport departurePort) {
        this.departurePort = departurePort;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
