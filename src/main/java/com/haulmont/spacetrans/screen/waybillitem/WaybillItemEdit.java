package com.haulmont.spacetrans.screen.waybillitem;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.WaybillItem;

@UiController("st_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
public class WaybillItemEdit extends StandardEditor<WaybillItem> {
}
