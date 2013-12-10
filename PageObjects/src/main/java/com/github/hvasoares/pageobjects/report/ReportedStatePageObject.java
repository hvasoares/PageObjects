package com.github.hvasoares.pageobjects.report;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public class ReportedStatePageObject extends ProxyStatePageObjectAdapter  {

	private ReportContext reportContext;
	 
	private PageLoadedWait pageLoadedWait;
	
	public ReportedStatePageObject(ReportContext reportContext) {
		this.reportContext = reportContext;
		this.pageLoadedWait = new PageLoadedWait();
	}
	 
	@Override 
	public StatePageObject click(String alias) {
		if ( !shouldTakeScreenshot( UserEvents.CLICK ) ) {
			return getInner().click(alias);
		} else { 
			StatePageObject statePageObject = getInner().click(alias);
			reportEvent( "click" );  
			return statePageObject;
		}
	}

	@Override
	public StatePageObject fill(String field, String value) {
		if ( !shouldTakeScreenshot( UserEvents.FILL ) ) {
			return getInner().fill(field, value); 
		} else { 
			StatePageObject statePageObject = getInner().fill(field, value);
			reportEvent("fill"); 
			return statePageObject;
		}		
	}

	@Override
	public void setState(String stateName) {
		if ( !shouldTakeScreenshot( UserEvents.CHANGE_PAGE) ) {
			getInner().setState(stateName); 
		} else { 
			getInner().setState(stateName);
			pageLoadedWait.waitForPageLoaded();
			reportEvent("change-page" ); 
		}		 
	}

	@Override
	public StatePageObject doubleClick(String alias) {
		if ( !shouldTakeScreenshot( UserEvents.DOUBLE_CLICK ) ) {
			return getInner().doubleClick(alias);
		} else { 
			StatePageObject statePageObject = getInner().doubleClick(alias);
			reportEvent("double-click");  
			return statePageObject;
		}
	}

	private boolean shouldTakeScreenshot( String event ) {
		return reportContext.getSettings().isEnabled() 
				&& reportContext.getSettings().getEnabledEvents().contains(event);
	}
		
	private void reportEvent( String event ){
		for ( ReportStrategy strategy : reportContext.getStrategies() ){
			strategy.report(  reportContext, event );
		}
		reportContext.nextStep();
	}	
}
