package com.haulmont.spacetrans.listener;

import com.haulmont.spacetrans.app.WaybillChargeCalculator;
import com.haulmont.spacetrans.entity.Waybill;
import com.haulmont.spacetrans.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("st_WaybillItemEventListener")
public class WaybillItemEventListener {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private WaybillChargeCalculator waybillChargeCalculator;

    @EventListener
    public void onWaybillItemSaving(EntitySavingEvent<WaybillItem> event) {
        boolean changed = waybillChargeCalculator.calculateItemCharge(event.getEntity());
        if (changed) {
            waybillChargeCalculator.calculateWaybillTotals(event.getEntity().getWaybill());
        }
    }

    @EventListener
    public void onWaybillItemChangedBeforeCommit(EntityChangedEvent<WaybillItem> event) {
        if (EntityChangedEvent.Type.DELETED.equals(event.getType())) {
            Id<Waybill> id = event.getChanges().getOldValue("waybill");
            assert id != null;
            Waybill waybill = dataManager.load(id).one();
            waybillChargeCalculator.calculateWaybillTotals(waybill);
            dataManager.save(waybill);
        }
    }


}
