package com.seblink.hybris.flexiblesearchtests.service.impl;

import com.seblink.hybris.flexiblesearchtests.service.AbstractIntegrationTest;
import com.seblink.hybris.flexiblesearchtests.service.OnlySuperTypesSyntaxService;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MySubModelModel;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@IntegrationTest
public class OnlySuperTypesSyntaxServiceImplTest extends AbstractIntegrationTest {

    @Resource
    private OnlySuperTypesSyntaxService onlySuperTypesSyntaxService;

    private MyModelModel myModel1;
    private MyModelModel myModel2;
    private MyModelModel myModel3;

    private MySubModelModel mySubModel1;
    private MySubModelModel mySubModel2;
    private MySubModelModel mySubModel3;

    @Before
    public void setUp() throws Exception {
        myModel1 = newMyModel().save();
        myModel2 = newMyModel().save();
        myModel3 = newMyModel().save();

        mySubModel1 = newMySubModel().save();
        mySubModel2 = newMySubModel().save();
        mySubModel3 = newMySubModel().save();
    }

    @Test
    public void testFindAll() throws Exception {
        List<MyModelModel> result = onlySuperTypesSyntaxService.findAll();

        assertThat(result).containsOnly(myModel1, myModel2, myModel3, mySubModel1, mySubModel2, mySubModel3);
    }

    @Test
    public void testFindAllExcludingSubtypes() throws Exception {
        List<MyModelModel> result = onlySuperTypesSyntaxService.findAllExcludingSubtypes();

        assertThat(result).containsOnly(myModel1, myModel2, myModel3);
    }
}
