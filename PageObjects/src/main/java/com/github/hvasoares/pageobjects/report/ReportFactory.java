package com.github.hvasoares.pageobjects.report;

import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public final class ReportFactory {
	
	public static ProxyStatePageObjectAdapter createReportedStatePageObject(){
		return 	new ReportedStatePageObject(ReportContextFactory.createReportContext() ); 
	}
	  	
}
