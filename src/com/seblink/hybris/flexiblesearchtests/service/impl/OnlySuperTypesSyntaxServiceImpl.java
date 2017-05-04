package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.service.OnlySuperTypesSyntaxService;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OnlySuperTypesSyntaxServiceImpl implements OnlySuperTypesSyntaxService {

    private final FlexibleSearchService flexibleSearchService;

    @Autowired
    public OnlySuperTypesSyntaxServiceImpl(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<MyModelModel> findAll() {
        final String statement =
                "SELECT {pk} FROM {MyModel}";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }

    @Override
    public List<MyModelModel> findAllExcludingSubtypes() {
        final String statement =
                "SELECT {pk} FROM {MyModel!}";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }
}
