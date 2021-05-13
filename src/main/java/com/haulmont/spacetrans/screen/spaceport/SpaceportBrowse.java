package com.haulmont.spacetrans.screen.spaceport;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Spaceport;

@UiController("st_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
public class SpaceportBrowse extends StandardLookup<Spaceport> {
}
