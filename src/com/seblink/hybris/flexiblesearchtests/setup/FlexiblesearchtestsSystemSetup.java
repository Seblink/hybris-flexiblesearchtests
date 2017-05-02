/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.seblink.hybris.flexiblesearchtests.setup;

import static com.seblink.hybris.flexiblesearchtests.constants.FlexiblesearchtestsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.seblink.hybris.flexiblesearchtests.constants.FlexiblesearchtestsConstants;
import com.seblink.hybris.flexiblesearchtests.service.FlexiblesearchtestsService;


@SystemSetup(extension = FlexiblesearchtestsConstants.EXTENSIONNAME)
public class FlexiblesearchtestsSystemSetup
{
	private final FlexiblesearchtestsService flexiblesearchtestsService;

	public FlexiblesearchtestsSystemSetup(final FlexiblesearchtestsService flexiblesearchtestsService)
	{
		this.flexiblesearchtestsService = flexiblesearchtestsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		flexiblesearchtestsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return FlexiblesearchtestsSystemSetup.class.getResourceAsStream("/flexiblesearchtests/sap-hybris-platform.png");
	}
}
