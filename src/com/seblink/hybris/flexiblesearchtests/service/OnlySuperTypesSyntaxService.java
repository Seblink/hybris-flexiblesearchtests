package com.seblink.hybris.flexiblesearchtests.service;

import de.hybris.platform.core.model.MyModelModel;

import java.util.List;

public interface OnlySuperTypesSyntaxService {

    List<MyModelModel> findAll();

    List<MyModelModel> findAllExcludingSubtypes();
}
