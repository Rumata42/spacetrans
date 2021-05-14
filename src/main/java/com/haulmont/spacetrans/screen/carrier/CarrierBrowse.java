package com.haulmont.spacetrans.screen.carrier;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Carrier;

@UiController("st_Carrier.browse")
@UiDescriptor("carrier-browse.xml")
@LookupComponent("table")
public class CarrierBrowse extends MasterDetailScreen<Carrier> {
}
