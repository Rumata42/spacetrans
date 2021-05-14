package com.haulmont.spacetrans.screen.company;

import io.jmix.ui.screen.*;
import com.haulmont.spacetrans.entity.Company;

@UiController("st_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
public class CompanyEdit extends StandardEditor<Company> {
}
