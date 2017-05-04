package com.seblink.hybris.flexiblesearchtests.service;

import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MyReferenceModel;

import java.util.Collection;
import java.util.List;

/**
 * Service to perform FlexibleSearch queries using the 'IN (...)' syntax.
 */
public interface InSyntaxService {

    List<MyModelModel> findUsingInApiWithEnums(Collection<MyEnum> enums);

    List<MyModelModel> findUsingBadInApiWithEnums(Collection<MyEnum> enums);

    List<MyModelModel> findUsingInApiWithReferences(Collection<MyReferenceModel> references);

    List<MyModelModel> findUsingBadInApiWithReferences(Collection<MyReferenceModel> references);
}
