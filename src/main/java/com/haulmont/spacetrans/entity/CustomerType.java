package com.haulmont.spacetrans.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum CustomerType implements EnumClass<String> {

    INDIVIDUAL("Individual", Individual.class),
    COMPANY("Company", Company.class);

    private final String id;
    private final Class<? extends Customer> clazz;

    CustomerType(String value, Class<? extends Customer> clazz) {
        this.id = value;
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public Class<? extends Customer> getClazz() {
        return clazz;
    }

    @Nullable
    public static CustomerType fromId(String id) {
        for (CustomerType at : CustomerType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
