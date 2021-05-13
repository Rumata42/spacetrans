package com.haulmont.spacetrans.screen.gas;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Gas;

@UiController("st_GasEntity.browse")
@UiDescriptor("gas-browse.xml")
@LookupComponent("gasesTable")
public class GasBrowse extends StandardLookup<Gas> {
}
