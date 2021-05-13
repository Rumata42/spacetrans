package com.haulmont.spacetrans.listener;

import com.haulmont.spacetrans.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.event.AttributeChanges;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("st_SpaceportEventListener")
public class SpaceportEventListener {

    private static final String IS_DEFAULT_FIELD = "isDefault";

    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onSpaceportChangedBeforeCommit(EntityChangedEvent<Spaceport> event) {
        AttributeChanges changes = event.getChanges();
        if (changes.isChanged(IS_DEFAULT_FIELD) && !Boolean.TRUE.equals(changes.getOldValue(IS_DEFAULT_FIELD))) {
            Spaceport spaceport = dataManager.load(event.getEntityId()).one();
            if (!spaceport.getIsDefault()) {
                return;
            }
            dataManager.load(Spaceport.class)
                    .condition(LogicalCondition.and(
                            PropertyCondition.equal(IS_DEFAULT_FIELD, true),
                            PropertyCondition.notEqual("id", event.getEntityId().getValue())
                    ))
                    .optional()
                    .ifPresent(previousDefaultSpaceport -> {
                        previousDefaultSpaceport.setIsDefault(false);
                        dataManager.save(previousDefaultSpaceport);
                    });
            // TODO need to refresh spaceport list
        }
    }

}
