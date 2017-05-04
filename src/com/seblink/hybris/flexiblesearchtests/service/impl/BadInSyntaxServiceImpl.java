package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import com.seblink.hybris.flexiblesearchtests.service.BadInSyntaxService;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MyReferenceModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class BadInSyntaxServiceImpl implements BadInSyntaxService {

     private final FlexibleSearchService flexibleSearchService;

    @Autowired
    public BadInSyntaxServiceImpl(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<MyModelModel> findWithEnums(Collection<MyEnum> enums) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myEnum} IN ?enums";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("enums", enums);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }

    @Override
    public List<MyModelModel> findWithReferences(Collection<MyReferenceModel> references) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myReference} IN ?references";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("references", references);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }
}
