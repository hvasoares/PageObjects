package com.github.hvasoares.pageobjects.report;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class ReportListener extends RunListener {

	private ReportContextI reportContext;
	
	public ReportListener( ReportContextI reportContext ) {
		this.reportContext = reportContext;
	}
	
	@Override
	public void testStarted(Description description) throws Exception {
		reportContext.setCurrentTest( description );		
		super.testStarted(description);
	} 
}
