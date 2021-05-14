package com.haulmont.spacetrans.screen.waybill;

import com.haulmont.spacetrans.app.SpaceportService;
import com.haulmont.spacetrans.entity.AstronomicalBody;
import com.haulmont.spacetrans.entity.Carrier;
import com.haulmont.spacetrans.entity.Customer;
import com.haulmont.spacetrans.entity.CustomerType;
import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import com.haulmont.spacetrans.entity.Spaceport;
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
import java.util.Objects;
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
    @Autowired
    private ComboBox<Carrier> carrierField;

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

    @Subscribe("departurePortField")
    public void onDeparturePortFieldValueChange(HasValue.ValueChangeEvent<Spaceport> event) {
        Spaceport spaceport = event.getValue();
        if (spaceport == null) {
            departurePortAstronomicalBody.setValue(null);
            setCarriers(false);
        } else {
            AstronomicalBody astronomicalBody = departurePortAstronomicalBody.getValue();
            if (!Objects.equals(spaceport.getMoon(), astronomicalBody) && !Objects.equals(spaceport.getPlanet(),astronomicalBody)) {
                departurePortAstronomicalBody.setValue(null);
            }
            if (this.getEditedEntity().getDestinationPort() != null) {
                setCarriers(true);
            }
        }
    }

    @Subscribe("destinationPortField")
    public void onDestinationPortFieldValueChange(HasValue.ValueChangeEvent<Spaceport> event) {
        Spaceport spaceport = event.getValue();
        if (spaceport == null) {
            destinationPortAstronomicalBody.setValue(null);
            setCarriers(false);
        } else {
            AstronomicalBody astronomicalBody = destinationPortAstronomicalBody.getValue();
            if (!Objects.equals(spaceport.getMoon(), astronomicalBody) && !Objects.equals(spaceport.getPlanet(),astronomicalBody)) {
                destinationPortAstronomicalBody.setValue(null);
            }
            if (this.getEditedEntity().getDeparturePort() != null) {
                setCarriers(true);
            }
        }
    }


    private void setCarriers(boolean enabled) {
        if (!enabled) {
            carrierField.clear();
            carrierField.setEnabled(false);
            return;
        }
        List<Carrier> carriers = dataManager.load(Carrier.class)
                .query("e.spaceports = ?1 and e.spaceports = ?2",
                        getEditedEntity().getDeparturePort(), getEditedEntity().getDestinationPort())
                .list();
        if (carrierField.getValue() != null && !carriers.contains(carrierField.getValue())) {
            carrierField.setValue(null);
        }
        carrierField.setOptionsList(carriers);
        carrierField.setEnabled(true);
    }

}
