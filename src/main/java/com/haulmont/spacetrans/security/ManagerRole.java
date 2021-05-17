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
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Manager", code = "manager")
public interface ManagerRole {

    @MenuPolicy(menuIds = {"planetarium", "st_GasEntity.browse", "st_Moon.browse", "st_Planet.browse", "st_Spaceport.browse", "st_Carrier.browse", "transportation", "st_Individual.browse", "st_Company.browse", "st_Waybill.browse", "st_Discount.browse"})
    void commonMenus();

    @ScreenPolicy(screenIds = {"st_GasEntity.browse", "st_Moon.browse", "st_Planet.browse", "st_Spaceport.browse", "st_Carrier.browse", "st_Individual.browse", "st_Company.browse", "st_Waybill.browse", "st_Discount.browse", "st_Atmosphere.browse", "st_Atmosphere.edit", "st_Company.edit", "st_GasEntity.edit", "st_Individual.edit", "st_LoginScreen", "st_MainScreen", "st_Moon.edit", "st_Planet.edit", "st_Spaceport.edit", "st_User.edit", "st_Waybill.edit", "st_WaybillItem.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.ALL)
    void astronomicalBody();

    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.ALL)
    void atmosphere();

    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.ALL)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Carrier.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Carrier.class, actions = EntityPolicyAction.ALL)
    void carrier();

    @EntityAttributePolicy(entityClass = Company.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Company.class, actions = EntityPolicyAction.ALL)
    void company();

    @EntityAttributePolicy(entityClass = Coordinates.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Coordinates.class, actions = EntityPolicyAction.ALL)
    void coordinates();

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.ALL)
    void customer();

    @EntityAttributePolicy(entityClass = Dimensions.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Dimensions.class, actions = EntityPolicyAction.ALL)
    void dimensions();

    @EntityAttributePolicy(entityClass = Discount.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Discount.class, actions = EntityPolicyAction.ALL)
    void discount();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.ALL)
    void gas();

    @EntityAttributePolicy(entityClass = Individual.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Individual.class, actions = EntityPolicyAction.ALL)
    void individual();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.ALL)
    void moon();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.ALL)
    void planet();

    @EntityAttributePolicy(entityClass = Spaceport.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Spaceport.class, actions = EntityPolicyAction.ALL)
    void spaceport();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Waybill.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Waybill.class, actions = EntityPolicyAction.ALL)
    void waybill();

    @EntityAttributePolicy(entityClass = WaybillItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = WaybillItem.class, actions = EntityPolicyAction.ALL)
    void waybillItem();

    @SpecificPolicy(resources = "ui.loginToUi")
    void specify();
}
