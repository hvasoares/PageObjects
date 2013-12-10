package com.github.hvasoares.pageobjects.report;

import org.junit.runner.notification.RunListener;

public class ReportListenerFactory {

	public static RunListener createListener(){
	
		return new ReportListener( ReportContextFactory.createReportContext( ) );
	}
}
