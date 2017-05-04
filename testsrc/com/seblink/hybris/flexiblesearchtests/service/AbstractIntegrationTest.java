package com.seblink.hybris.flexiblesearchtests.service;


import com.seblink.hybris.flexiblesearchtests.enums.MyEnum;
import de.hybris.platform.core.model.MyModelModel;
import de.hybris.platform.core.model.MyReferenceModel;
import de.hybris.platform.core.model.MySubModelModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

public abstract class AbstractIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private ModelService modelService;

    protected MyModelBuilder newMyModel() {
        return new MyModelBuilder();
    }

    protected MySubModelBuilder newMySubModel() {
        return new MySubModelBuilder();
    }

    protected MyReferenceBuilder newMyReference() {
        return new MyReferenceBuilder();
    }

    protected class MyModelBuilder {

        private MyModelModel instance;

        private MyModelBuilder() {
            this.instance = modelService.create(MyModelModel.class);
        }

        public MyModelBuilder withMyReference(MyReferenceModel myReference) {
            this.instance.setMyReference(myReference);
            return this;
        }

        public MyModelBuilder withMyEnum(MyEnum myEnum) {
            this.instance.setMyEnum(myEnum);
            return this;
        }

        public MyModelModel save() {
            modelService.save(this.instance);
            return this.instance;
        }

    }

    protected class MySubModelBuilder {

        private MySubModelModel instance;

        private MySubModelBuilder() {
            this.instance = modelService.create(MySubModelModel.class);
        }

        public MySubModelBuilder withMyReference(MyReferenceModel myReference) {
            this.instance.setMyReference(myReference);
            return this;
        }

        public MySubModelBuilder withMyEnum(MyEnum myEnum) {
            this.instance.setMyEnum(myEnum);
            return this;
        }

        public MySubModelModel save() {
            modelService.save(this.instance);
            return this.instance;
        }

    }

    protected class MyReferenceBuilder {

        private final MyReferenceModel instance;

        private MyReferenceBuilder() {
            this.instance = modelService.create(MyReferenceModel.class);
        }

        public MyReferenceModel save() {
            modelService.save(this.instance);
            return this.instance;
        }
    }
}
