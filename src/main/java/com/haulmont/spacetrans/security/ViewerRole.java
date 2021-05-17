package com.haulmont.spacetrans.security;

import com.haulmont.spacetrans.entity.Gas;
import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import com.haulmont.spacetrans.entity.Spaceport;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.EntityPolicyContainer;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import static com.haulmont.spacetrans.security.ViewerRole.CODE;

@ResourceRole(name = "Viewer", code = CODE)
public interface ViewerRole {

    String CODE = "viewer";

    @EntityPolicyContainer({
            @EntityPolicy(entityClass = Gas.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Planet.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Moon.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Spaceport.class, actions = {EntityPolicyAction.READ})
    })
    @MenuPolicy(menuIds = {"st_GasEntity.browse", "st_Planet.browse", "st_Moon.browse", "st_Spaceport.browse", "st_Carrier.browse"})
    @ScreenPolicy(screenIds = {"st_GasEntity.browse", "st_GasEntity.edit", "st_Planet.browse", "st_Planet.edit", "st_Moon.browse", "st_Moon.edit", "st_Spaceport.browse", "st_Spaceport.edit"})
    @SpecificPolicy(resources = {"*", "ui.loginToUi"})
    void viewer();

}
