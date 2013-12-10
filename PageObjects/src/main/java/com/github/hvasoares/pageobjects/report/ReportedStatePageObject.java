package com.github.hvasoares.pageobjects.report;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public class ReportedStatePageObject extends ProxyStatePageObjectAdapter  {

	private ReportContextI reportContext;
	 
	private PageLoadedWait pageLoadedWait;
	
	public ReportedStatePageObject(ReportContextI reportContext) {
		this(reportContext,new PageLoadedWait());
	}
	
	public ReportedStatePageObject(ReportContextI reportContext,PageLoadedWait pageLoadedWait){
		this.reportContext = reportContext;
		this.pageLoadedWait = pageLoadedWait;
	}
	 
	@Override 
	public StatePageObject click(String alias) {
		StatePageObject result = getInner().click(alias);
		reportContext.tryReportEvent( UserEvents.CLICK );  
		return result;
	}

	@Override
	public StatePageObject fill(String field, String value) {
		StatePageObject result = getInner().fill(field, value);
		reportContext.tryReportEvent( UserEvents.FILL );  
		return result;		
	}

	@Override
	public void setState(String stateName) {
		getInner().setState(stateName);
		pageLoadedWait.waitForPageLoaded();
		reportContext.tryReportEvent( UserEvents.CHANGE_PAGE );  		 
	}

	@Override
	public StatePageObject doubleClick(String alias) {
		StatePageObject result = getInner().doubleClick(alias);
		reportContext.tryReportEvent( UserEvents.DOUBLE_CLICK);  
		return result;
	}
}
