package com.haulmont.spacetrans.screen.discount;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Discount;

@UiController("st_Discount.browse")
@UiDescriptor("discount-browse.xml")
@LookupComponent("table")
public class DiscountBrowse extends MasterDetailScreen<Discount> {
}
