package com.github.hvasoares.pageobjects.report;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class ReportContextTest {

	@Mock private ReportSettings settings;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	
	@Mock private ReportStrategy strategy1;
	@Mock private ReportStrategy strategy2;
	private ReportContext instance;

	@Test
	public void testSeCurrentStepIsIncrementedByOne() {		
		
		instance = new ReportContext(
				settings,
				Arrays.asList(strategy1,strategy2)
		);
		
		ctx.checking(new Expectations(){{
			oneOf(settings).isEnabled();
			will(returnValue(true));
			oneOf(settings).getEnabledEvents();
			will(returnValue(Arrays.asList("someEvent")));
			
			oneOf(strategy1).report(instance, "someEvent");
			oneOf(strategy2).report(instance, "someEvent");
		}});
		
		int currentStep = instance.getCurrentStep();
		instance.tryReportEvent("someEvent");
		assertEquals(currentStep+1,instance.getCurrentStep());
	}

}
