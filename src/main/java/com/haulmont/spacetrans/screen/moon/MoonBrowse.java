package com.haulmont.spacetrans.screen.moon;

import io.jmix.ui.component.Table;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Moon;

import javax.inject.Inject;

@UiController("st_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
public class MoonBrowse extends StandardLookup<Moon> {

    @Inject
    private InstanceContainer<Moon> selectedMoon;

    @Subscribe("moonsTable")
    public void onMoonsTableSelection(Table.SelectionEvent<Moon> event) {
        Moon moon = event.getSelected().stream().findFirst().orElse(null);
        selectedMoon.setItem(moon);

    }
}
