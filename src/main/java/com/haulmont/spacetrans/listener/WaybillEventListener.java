package com.haulmont.spacetrans.listener;

import com.haulmont.spacetrans.entity.User;
import com.haulmont.spacetrans.entity.Waybill;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("st_WaybillEventListener")
public class WaybillEventListener {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @EventListener
    public void onWaybillSaving(EntitySavingEvent<Waybill> event) {
        if (!event.isNewEntity()) {
            return;
        }
        User creator = (User) currentAuthentication.getAuthentication().getPrincipal();
        event.getEntity().setCreator(creator);
    }
}
