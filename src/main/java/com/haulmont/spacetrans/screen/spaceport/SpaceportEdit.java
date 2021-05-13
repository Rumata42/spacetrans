package com.haulmont.spacetrans.screen.spaceport;

import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.ValuePicker;
import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Spaceport;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Spaceport.edit")
@UiDescriptor("spaceport-edit.xml")
@EditedEntityContainer("spaceportDc")
public class SpaceportEdit extends StandardEditor<Spaceport> {

    @Autowired
    private EntityPicker<Planet> planetField;
    @Autowired
    private EntityPicker<Moon> moonField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        Spaceport spaceport = super.getEditedEntity();
        if (spaceport.getPlanet() != null) {
            moonField.setEnabled(false);
        }
        if (spaceport.getMoon() != null) {
            planetField.setEnabled(false);
        }
    }

    @Subscribe("moonField")
    public void onMoonFieldValueChange(HasValue.ValueChangeEvent<Moon> event) {
        planetField.setEnabled(event.getValue() == null);
    }

    @Subscribe("planetField")
    public void onPlanetFieldValueChange(HasValue.ValueChangeEvent<Planet> event) {
        moonField.setEnabled(event.getValue() == null);
    }

}
