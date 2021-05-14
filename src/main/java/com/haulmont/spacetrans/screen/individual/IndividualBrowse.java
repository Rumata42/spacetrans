package com.haulmont.spacetrans.screen.individual;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Individual;

@UiController("st_Individual.browse")
@UiDescriptor("individual-browse.xml")
@LookupComponent("individualsTable")
public class IndividualBrowse extends StandardLookup<Individual> {
}
