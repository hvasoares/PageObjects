package com.github.hvasoares.pageobjects.report;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ReportSettingsTest {

	@Test
	public void reportSettingsShouldHaveDefaultBehavior() {
		
		ReportSettings tested = new ReportSettings(null, null , null);
		
		Assert.assertEquals( System.getProperty("user.home"), tested.getPath() );
		Assert.assertEquals( true , tested.getStrategies().isEmpty() );
	}
	
	@Test
	public void reportSettingsShouldUseProvideSettings(){
		List<String> strategies = Lists.newArrayList("screenshot");
		List<String> events = Collections.emptyList();
		ReportSettings tested = new ReportSettings( "/home/test/report" , strategies, events );
		
		Assert.assertEquals(  "/home/test/report" , tested.getPath() );
		Assert.assertEquals( "screenshot" , tested.getStrategies().get(0));
		Assert.assertEquals( UserEvents.CHANGE_PAGE , tested.getEnabledEvents().get(0));
	}

}
