package com.haulmont.spacetrans.screen.gas;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Gas;

@UiController("st_GasEntity.edit")
@UiDescriptor("gas-edit.xml")
@EditedEntityContainer("gasDc")
public class GasEdit extends StandardEditor<Gas> {
}
