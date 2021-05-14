package com.haulmont.spacetrans.screen.waybill;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Waybill;

@UiController("st_Waybill.browse")
@UiDescriptor("waybill-browse.xml")
@LookupComponent("waybillsTable")
public class WaybillBrowse extends StandardLookup<Waybill> {
}
