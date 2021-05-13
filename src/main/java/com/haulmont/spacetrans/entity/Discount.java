package com.haulmont.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_DISCOUNT")
@Entity(name = "st_Discount")
public class Discount {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "GRADE", nullable = false, unique = true)
    @NotNull
    private Integer grade;

    @DecimalMax("100")
    @DecimalMin("0")
    @Column(name = "VALUE_", precision = 5, scale = 2)
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public CustomerGrade getGrade() {
        return grade == null ? null : CustomerGrade.fromId(grade);
    }

    public void setGrade(CustomerGrade grade) {
        this.grade = grade == null ? null : grade.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
