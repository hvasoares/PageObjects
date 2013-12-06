package com.github.hvasoares.pageobjects.report;

import org.junit.Assert;
import org.junit.Test;

public class ReportContextTest {

	@Test
	public void testSeCurrentStepIsIncrementedByOne() {		
		
		ReportContext reportContext = ReportContextFactory.createReportContext();
		
		//given
		int currentStep = reportContext.getCurrentStep();
		// when
		reportContext.nextStep();
		// then
		int newCurrentStep = reportContext.getCurrentStep();
		
		Assert.assertTrue( newCurrentStep == ( currentStep + 1 ) );		
	}

}
