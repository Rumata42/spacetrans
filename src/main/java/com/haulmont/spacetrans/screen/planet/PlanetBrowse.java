package com.haulmont.spacetrans.screen.planet;

import com.haulmont.spacetrans.app.PlanetImporter;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Planet;
import liquibase.util.csv.CSVReader;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStreamReader;

@UiController("st_Planet.browse")
@UiDescriptor("planet-browse.xml")
@LookupComponent("planetsTable")
public class PlanetBrowse extends StandardLookup<Planet> {

    @Inject
    private InstanceContainer<Planet> selectedPlanet;

    @Inject
    private CollectionLoader<Planet> planetsDl;

    @Inject
    private PlanetImporter planetImporter;


    @Subscribe("planetsTable")
    public void onPlanetsTableSelection(Table.SelectionEvent<Planet> event) {
        Planet planet = event.getSelected().stream().findFirst().orElse(null);
        selectedPlanet.setItem(planet);
    }

    @Subscribe("importPlanetsBtn")
    public void onImportPlanetsBtnFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) throws IOException {
        SingleFileUploadField source = (SingleFileUploadField) event.getSource();
        try(CSVReader csvReader = new CSVReader(new InputStreamReader(source.getFileContent()))) {
            planetImporter.importPlanets(csvReader.readAll());
        }
        planetsDl.load();
    }

}
