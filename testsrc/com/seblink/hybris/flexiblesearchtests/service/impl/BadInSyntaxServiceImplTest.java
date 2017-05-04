package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import com.seblink.hybris.flexiblesearchtests.service.AbstractIntegrationTest;
import com.seblink.hybris.flexiblesearchtests.service.BadInSyntaxService;
import de.hybris.bootstrap.annotations.IntegrationTest;
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
public class BadInSyntaxServiceImplTest extends AbstractIntegrationTest {

    @Resource
    private BadInSyntaxService badInSyntaxService;

    private MyReferenceModel reference1;
    private MyReferenceModel reference2;
    private MyReferenceModel reference3;

    @Before
    public void setUp() throws Exception {
        reference1 = newMyReference().save();
        reference2 = newMyReference().save();
        reference3 = newMyReference().save();

        newMyModel().withMyEnum(MyEnum.VALUE_1).withMyReference(reference1).save();
        newMyModel().withMyEnum(MyEnum.VALUE_1).withMyReference(reference1).save();
        newMyModel().withMyEnum(MyEnum.VALUE_2).withMyReference(reference2).save();
        newMyModel().withMyEnum(MyEnum.VALUE_3).withMyReference(reference3).save();
    }

    @Test
    public void testBadInApi_enums_null() throws Exception {
        try {
            badInSyntaxService.findWithEnums(null);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is required, null given for key: enums");
        }
    }

    @Test
    public void testBadInApi_enums_emptyCollection() throws Exception {
        try {
            badInSyntaxService.findWithEnums(new ArrayList<>());
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is instanceof Collection but cannot be empty collection");
        }
    }

    @Test
    public void testBadInApi_enums_singleValue() throws Exception {
        List<MyEnum> input = Collections.singletonList(MyEnum.VALUE_1);

        try {
            badInSyntaxService.findWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_enums_multipleValues() throws Exception {
        List<MyEnum> input = Arrays.asList(MyEnum.VALUE_2, MyEnum.VALUE_3);

        try {
            badInSyntaxService.findWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_enums_singleValue_isNull() throws Exception {
        List<MyEnum> input = Collections.singletonList(null);

        try {
            badInSyntaxService.findWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_enums_multipleValues_containsNull() throws Exception {
        List<MyEnum> input = Arrays.asList(MyEnum.VALUE_2, null, MyEnum.VALUE_3);

        try {
            badInSyntaxService.findWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testBadInApi_enums_multipleValues_firstValueIsNull() throws Exception {
        List<MyEnum> input = Arrays.asList(null, MyEnum.VALUE_2, MyEnum.VALUE_3);

        try {
            badInSyntaxService.findWithEnums(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testBadInApi_references_null() throws Exception {
        try {
            badInSyntaxService.findWithReferences(null);
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is required, null given for key: references");
        }
    }

    @Test
    public void testBadInApi_references_emptyCollection() throws Exception {
        try {
            badInSyntaxService.findWithReferences(new ArrayList<>());
            shouldHaveThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Value is instanceof Collection but cannot be empty collection");
        }
    }

    @Test
    public void testBadInApi_references_singleValue() throws Exception {
        List<MyReferenceModel> input = Collections.singletonList(reference1);

        try {
            badInSyntaxService.findWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_references_multipleValues() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(reference2, reference3);

        try {
            badInSyntaxService.findWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_references_singleValue_isNull() throws Exception {
        List<MyReferenceModel> input = Collections.singletonList(null);

        try {
            badInSyntaxService.findWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).contains("SQL search error - unexpected token: ? required: ( in statement");
        }
    }

    @Test
    public void testBadInApi_references_multipleValues_containsNull() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(reference2, null, reference3);

        try {
            badInSyntaxService.findWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }

    @Test
    public void testBadInApi_references_multipleValues_firstValueIsNull() throws Exception {
        List<MyReferenceModel> input = Arrays.asList(null, reference2, reference3);

        try {
            badInSyntaxService.findWithReferences(input);
            shouldHaveThrown(FlexibleSearchException.class);
        } catch (FlexibleSearchException e) {
            assertThat(e.getMessage()).isNull();
            assertThat(e.getCause()).isInstanceOf(de.hybris.platform.jalo.flexiblesearch.FlexibleSearchException.class);
            assertThat(e.getCause().getCause()).isInstanceOf(NullPointerException.class);
        }
    }
}