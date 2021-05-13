package com.haulmont.spacetrans.app;

import com.haulmont.spacetrans.entity.Planet;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(PlanetImporter.NAME)
public class PlanetImporter {
    public static final String NAME = "_PlanetImporter";

    private final DataManager dataManager;

    public PlanetImporter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void importPlanets(List<String[]> planetsInfo) {
        for (String[] planetInfo : planetsInfo) {
            Planet planet = dataManager.load(Planet.class)
                    .condition(PropertyCondition.equal("name", planetInfo[0]))
                    .optional()
                    .orElseGet(() -> {
                        Planet newPlanet = dataManager.create(Planet.class);
                        newPlanet.setName(planetInfo[0]);
                        return newPlanet;
                    });
            planet.setMass(parseDoubleSafe(planetInfo[2]));
            planet.setSemiMajorAxis(parseDoubleSafe(planetInfo[3]));
            planet.setOrbitalPeriod(parseDoubleSafe(planetInfo[4]));
            planet.setRotationPeriod(parseDoubleSafe(planetInfo[7]));
            planet.setRings("yes".equalsIgnoreCase(planetInfo[10]));
            dataManager.save(planet);
        }
    }


    private double parseDoubleSafe(String value) {
        if (value == null) return 0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ignore) {
            return 0;
        }
    }

}
