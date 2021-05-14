package com.haulmont.spacetrans.app;

import com.haulmont.spacetrans.entity.AstronomicalBody;
import com.haulmont.spacetrans.entity.Moon;
import com.haulmont.spacetrans.entity.Planet;
import com.haulmont.spacetrans.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.accessibility.AccessibleStreamable;
import java.util.Optional;

@Component("st_SpaceportService")
public class SpaceportService {

    @Autowired
    private DataManager dataManager;

    public Optional<Spaceport> getDefault(AstronomicalBody astronomicalBody) {
        if (astronomicalBody instanceof Planet) {
            return getDefault((Planet) astronomicalBody);
        } else {
            return getDefault((Moon) astronomicalBody);
        }
    }

    public Optional<Spaceport> getDefault(Planet planet) {
        return dataManager.load(Spaceport.class)
                .condition(LogicalCondition.and(
                        PropertyCondition.equal("isDefault", true),
                        PropertyCondition.equal("planet", planet)
                ))
                .optional();
    }

    public Optional<Spaceport> getDefault(Moon moon) {
        return dataManager.load(Spaceport.class)
                .condition(LogicalCondition.and(
                        PropertyCondition.equal("isDefault", true),
                        PropertyCondition.equal("moon", moon)
                ))
                .optional();
    }

}
