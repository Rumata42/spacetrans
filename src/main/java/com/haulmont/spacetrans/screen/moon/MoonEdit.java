package com.haulmont.spacetrans.screen.moon;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Moon;

@UiController("st_Moon.edit")
@UiDescriptor("moon-edit.xml")
@EditedEntityContainer("moonDc")
public class MoonEdit extends StandardEditor<Moon> {
}
