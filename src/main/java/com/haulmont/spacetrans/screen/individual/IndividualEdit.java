package com.haulmont.spacetrans.screen.individual;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Individual;

@UiController("st_Individual.edit")
@UiDescriptor("individual-edit.xml")
@EditedEntityContainer("individualDc")
public class IndividualEdit extends StandardEditor<Individual> {
}
