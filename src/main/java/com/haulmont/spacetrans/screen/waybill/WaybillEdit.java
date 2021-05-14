package com.haulmont.spacetrans.screen.waybill;

import com.haulmont.spacetrans.app.SpaceportService;
import com.haulmont.spacetrans.entity.AstronomicalBody;
import com.haulmont.spacetrans.entity.Customer;
import com.haulmont.spacetrans.entity.CustomerType;
import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Waybill;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UiController("st_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
public class WaybillEdit extends StandardEditor<Waybill> {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Metadata metadata;
    @Autowired
    private SpaceportService spaceportService;
    @Autowired
    private ComboBox<Customer> shipperField;
    @Autowired
    private EntityPicker<Customer> consigneeField;
    @Autowired
    private ComboBox<AstronomicalBody> departurePortAstronomicalBody;
    @Autowired
    private ComboBox<AstronomicalBody> destinationPortAstronomicalBody;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<AstronomicalBody> astronomicalBodies = new ArrayList<>();
        astronomicalBodies.addAll(dataManager.load(Planet.class).all().list());
        astronomicalBodies.addAll(dataManager.load(Moon.class).all().list());
        departurePortAstronomicalBody.setOptionsList(astronomicalBodies);
        destinationPortAstronomicalBody.setOptionsList(astronomicalBodies);
    }

    @Subscribe("shipperTypeField")
    public void onShipperTypeFieldValueChange(HasValue.ValueChangeEvent<CustomerType> event) {
        List<Customer> list = dataManager.load(event.getValue().getClazz()).all().list().stream()
                .map(Customer.class::cast)
                .collect(Collectors.toList());
        shipperField.setOptionsList(list);
        shipperField.setEnabled(true);
    }

    @Subscribe("consigneeTypeField")
    public void onConsigneeTypeFieldValueChange(HasValue.ValueChangeEvent<CustomerType> event) {
        consigneeField.setValueSource(null);
        consigneeField.setMetaClass(metadata.findClass(event.getValue().getClazz()));
        consigneeField.setEnabled(true);
    }

    @Subscribe("departurePortAstronomicalBody")
    public void onDeparturePortAstronomicalBodyValueChange(HasValue.ValueChangeEvent<AstronomicalBody> event) {
        Optional.ofNullable(event.getValue())
                .flatMap(spaceportService::getDefault)
                .ifPresent(spaceport -> getEditedEntity().setDeparturePort(spaceport));
    }

    @Subscribe("destinationPortAstronomicalBody")
    public void onDestinationPortAstronomicalBodyValueChange(HasValue.ValueChangeEvent<AstronomicalBody> event) {
        Optional.ofNullable(event.getValue())
                .flatMap(spaceportService::getDefault)
                .ifPresent(spaceport -> getEditedEntity().setDestinationPort(spaceport));
    }



}
