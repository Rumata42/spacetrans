package com.haulmont.spacetrans.security;

import com.haulmont.spacetrans.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(
        name = "Can see only own waybills",
        code = "restricted-waybills-role")
public interface RestrictedWaybillsRole {

    @JpqlRowLevelPolicy(
            entityClass = Waybill.class,
            where = "{E}.creator.id = :current_user_id")
    void creator();

}
