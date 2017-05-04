package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import com.seblink.hybris.flexiblesearchtests.service.InSyntaxService;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MyReferenceModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class InSyntaxServiceImpl implements InSyntaxService {

    private final FlexibleSearchService flexibleSearchService;

    @Autowired
    public InSyntaxServiceImpl(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<MyModelModel> findUsingInApiWithEnums(Collection<MyEnum> enums) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myEnum} IN (?enums)";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("enums", enums);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }


    @Override
    public List<MyModelModel> findUsingBadInApiWithEnums(Collection<MyEnum> enums) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myEnum} IN ?enums";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("enums", enums);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }

    @Override
    public List<MyModelModel> findUsingInApiWithReferences(Collection<MyReferenceModel> references) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myReference} IN (?references)";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("references", references);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }

    @Override
    public List<MyModelModel> findUsingBadInApiWithReferences(Collection<MyReferenceModel> references) {
        final String statement =
                "SELECT {pk} FROM {MyModel} WHERE {myReference} IN ?references";

        final FlexibleSearchQuery query = new FlexibleSearchQuery(statement);
        query.addQueryParameter("references", references);
        return flexibleSearchService.<MyModelModel>search(query).getResult();
    }
}
