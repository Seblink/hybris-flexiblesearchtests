package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import com.seblink.hybris.flexiblesearchtests.service.AbstractIntegrationTest;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MyReferenceModel;
import de.hybris.platform.servicelayer.search.exceptions.FlexibleSearchException;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.shouldHaveThrown;

@IntegrationTest
public class InSyntaxServiceImplTest extends AbstractIntegrationTest {

    @Resource
    private InSyntaxServiceImpl flexibleSearchInApiService;

    private MyReferenceModel reference1;
    private MyReferenceModel reference2;
    private MyReferenceModel reference3;

    private MyModelModel myModel1;
    private MyModelModel myModel2;
    private MyModelModel myModel3;
    private MyModelModel myModel4;

    @Before
    public void setUp() throws Exception {
        reference1 = newMyReference().save();
        reference2 = newMyReference().save();
        reference3 = newMyReference().save();

        myModel1 = newMyModel().withMyEnum(MyEnum.VALUE_1).withMyReference(reference1).save();
        myModel2 = newMyModel().withMyEnum(MyEnum.VALUE_1).withMyReference(reference1).save();
        myModel3 = newMyModel().withMyEnum(MyEnum.VALUE_2).withMyReference(reference2).save();
        myModel4 = newMyModel().withMyEnum(MyEnum.VALUE_3).withMyReference(reference3).save();
    }

    @Test
    public void testInApi_enums_null() throws Exception {
        try {
            flexibleSearchInApiService.findUsingInApiWithEnums(null);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is required, null given for key: enums");
        }
    }

    @Test
    public void testInApi_enums_emptyCollection() throws Exception {
        try {
            flexibleSearchInApiService.findUsingInApiWithEnums(new ArrayList<>());
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is instanceof Collection but cannot be empty collection");
        }
    }

    @Test
    public void testInApi_enums_singleValue() throws Exception {
        List<MyEnum> input = Collections.singletonList(MyEnum.VALUE_1);

        List<MyModelModel> result = flexibleSearchInApiService.findUsingInApiWithEnums(input);

        assertThat(result).containsExactlyInAnyOrder(myModel1, myModel2);
    }

    @Test
    public void testInApi_enums_multipleValues() throws Exception {
        List<MyEnum> input = Arrays.asList(MyEnum.VALUE_2, MyEnum.VALUE_3);

        List<MyModelModel> result = flexibleSearchInApiService.findUsingInApiWithEnums(input);

        assertThat(result).containsExactlyInAnyOrder(myModel3, myModel4);
    }

    @Test
    public void testInApi_enums_singleValue_isNull() throws Exception {
        List<MyEnum> input = Collections.singletonList(null);

        try {
            flexibleSearchInApiService.findUsingInApiWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("you cannot search for NULL values - use 'IS NULL' statements instead");
        }
    }

    @Test
    public void testInApi_enums_multipleValues_containsNull() throws Exception {
        List<MyEnum> input = Arrays.asList(MyEnum.VALUE_2, null, MyEnum.VALUE_3);

        try {
            flexibleSearchInApiService.findUsingInApiWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testInApi_enums_multipleValues_firstValueIsNull() throws Exception {
        List<MyEnum> input = Arrays.asList(null, MyEnum.VALUE_2, MyEnum.VALUE_3);

        try {
            flexibleSearchInApiService.findUsingInApiWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);

        }
    }

    @Test
    public void testInApi_references_null() throws Exception {
        try {
            flexibleSearchInApiService.findUsingInApiWithReferences(null);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is required, null given for key: references");
        }
    }

    @Test
    public void testInApi_references_emptyCollection() throws Exception {
        try {
            flexibleSearchInApiService.findUsingInApiWithReferences(new ArrayList<>());
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is instanceof Collection but cannot be empty collection");
        }
    }

    @Test
    public void testInApi_references_singleValue() throws Exception {
        List<MyReferenceModel> input = Collections.singletonList(reference1);

        List<MyModelModel> result = flexibleSearchInApiService.findUsingInApiWithReferences(input);

        assertThat(result).containsExactlyInAnyOrder(myModel1, myModel2);
    }

    @Test
    public void testInApi_references_multipleValues() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(reference2, reference3);

        List<MyModelModel> result = flexibleSearchInApiService.findUsingInApiWithReferences(input);

        assertThat(result).containsExactlyInAnyOrder(myModel3, myModel4);
    }

    @Test
    public void testInApi_references_singleValue_isNull() throws Exception {
        List<MyReferenceModel> input = Collections.singletonList(null);

        try {
            flexibleSearchInApiService.findUsingInApiWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("you cannot search for NULL values - use 'IS NULL' statements instead");
        }
    }

    @Test
    public void testInApi_references_multipleValues_containsNull() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(reference2, null, reference3);

        try {
            flexibleSearchInApiService.findUsingInApiWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testInApi_references_multipleValues_firstValueIsNull() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(null, reference2, reference3);

        try {
            flexibleSearchInApiService.findUsingInApiWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }
}