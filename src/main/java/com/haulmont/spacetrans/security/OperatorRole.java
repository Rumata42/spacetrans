package com.haulmont.spacetrans.security;

import com.haulmont.spacetrans.entity.AstronomicalBody;
import com.haulmont.spacetrans.entity.Atmosphere;
import com.haulmont.spacetrans.entity.AtmosphericGas;
import com.haulmont.spacetrans.entity.Carrier;
import com.haulmont.spacetrans.entity.Company;
import com.haulmont.spacetrans.entity.Coordinates;
import com.haulmont.spacetrans.entity.Customer;
import com.haulmont.spacetrans.entity.Dimensions;
import com.haulmont.spacetrans.entity.Discount;
import com.haulmont.spacetrans.entity.Gas;
import com.haulmont.spacetrans.entity.Individual;
import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import com.haulmont.spacetrans.entity.Spaceport;
import com.haulmont.spacetrans.entity.User;
import com.haulmont.spacetrans.entity.Waybill;
import com.haulmont.spacetrans.entity.WaybillItem;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.EntityPolicyContainer;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import static com.haulmont.spacetrans.security.OperatorRole.CODE;

@ResourceRole(name = "Operator", code = CODE)
public interface OperatorRole {

    String CODE = "operator";

    @EntityPolicyContainer({
            @EntityPolicy(entityClass = Gas.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Planet.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Moon.class, actions = {EntityPolicyAction.READ}),
            @EntityPolicy(entityClass = Spaceport.class, actions = {EntityPolicyAction.READ})
    })
    @MenuPolicy(menuIds = {"st_GasEntity.browse", "st_Planet.browse", "st_Moon.browse", "st_Spaceport.browse", "st_Carrier.browse", "transportation", "st_Individual.browse", "st_Company.browse", "st_Waybill.browse", "st_Discount.browse"})
    @ScreenPolicy(screenIds = {"st_GasEntity.browse", "st_GasEntity.edit", "st_Planet.browse", "st_Planet.edit", "st_Moon.browse", "st_Moon.edit", "st_Spaceport.browse", "st_Spaceport.edit", "st_Carrier.browse", "st_Individual.browse", "st_Company.browse", "st_Waybill.browse", "st_Discount.browse", "st_Atmosphere.browse", "st_Atmosphere.edit", "st_Company.edit", "st_Individual.edit", "st_LoginScreen", "st_MainScreen", "st_Waybill.edit", "st_WaybillItem.edit"})
    @SpecificPolicy(resources = {"*", "ui.loginToUi"})
    void operator();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.READ)
    void astronomicalBody();

    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.READ)
    void atmosphere();

    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.READ)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Carrier.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Carrier.class, actions = EntityPolicyAction.READ)
    void carrier();

    @EntityAttributePolicy(entityClass = Company.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Company.class, actions = {EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE, EntityPolicyAction.ALL})
    void company();

    @EntityAttributePolicy(entityClass = Coordinates.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Coordinates.class, actions = EntityPolicyAction.READ)
    void coordinates();

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = {EntityPolicyAction.UPDATE, EntityPolicyAction.DELETE, EntityPolicyAction.ALL})
    void customer();

    @EntityAttributePolicy(entityClass = Dimensions.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Dimensions.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Dimensions.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.ALL})
    void dimensions();

    @EntityAttributePolicy(entityClass = Discount.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Discount.class, actions = EntityPolicyAction.READ)
    void discount();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.READ)
    void gas();

    @EntityAttributePolicy(entityClass = Individual.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Individual.class, actions = EntityPolicyAction.ALL)
    void individual();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.READ)
    void moon();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.READ)
    void planet();

    @EntityAttributePolicy(entityClass = Spaceport.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Spaceport.class, actions = EntityPolicyAction.READ)
    void spaceport();

    @EntityAttributePolicy(entityClass = Waybill.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Waybill.class, actions = EntityPolicyAction.ALL)
    void waybill();

    @EntityAttributePolicy(entityClass = WaybillItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = WaybillItem.class, actions = EntityPolicyAction.ALL)
    void waybillItem();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();
}
