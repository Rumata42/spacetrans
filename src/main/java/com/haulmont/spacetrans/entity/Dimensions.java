package com.haulmont.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;

@JmixEntity(name = "st_Dimensions")
@Embeddable
public class Dimensions {
    @PositiveOrZero
    @Column(name = "LENGTH")
    private Double length;

    @PositiveOrZero
    @Column(name = "WIDTH")
    private Double width;

    @PositiveOrZero
    @Column(name = "HEIGHT")
    private Double height;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
