package com.github.hvasoares.pageobjects.report;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public class ReportedStatePageObject extends ProxyStatePageObjectAdapter {

	private ReportContext reportContext;
	 
	public ReportedStatePageObject(ReportContext reportContext) {
		this.reportContext = reportContext;
	}
	 
	@Override // /pasta/20-11-2013/suite{single-suite}/scenario/evidencias-ordem-evento.jpg 8895 5411
	public StatePageObject click(String alias) {
		if ( !shouldTakeScreenshot() ) {
			return getInner().click(alias);
		} else { 
			StatePageObject statePageObject = getInner().click(alias);
			reportEvent( "click" );  
			return statePageObject;
		}
	}

	@Override
	public StatePageObject fill(String field, String value) {
		if ( !shouldTakeScreenshot() ) {
			return getInner().fill(field, value); 
		} else { 
			StatePageObject statePageObject = getInner().fill(field, value);
			reportEvent("fill"); 
			return statePageObject;
		}		
	}

	@Override
	public void setState(String stateName) {
		if ( !shouldTakeScreenshot() ) {
			getInner().setState(stateName); 
		} else { 
			getInner().setState(stateName);
			reportEvent("change-page" ); 
		}		 
	}

	@Override
	public StatePageObject doubleClick(String alias) {
		if ( !shouldTakeScreenshot() ) {
			return getInner().doubleClick(alias);
		} else { 
			StatePageObject statePageObject = getInner().doubleClick(alias);
			reportEvent("double-click");  
			return statePageObject;
		}
	}


	private boolean shouldTakeScreenshot() {
		return reportContext.getSettings().isEnabled();
	}
		
	private void reportEvent( String event ){
		for ( ReportStrategy strategy : reportContext.getStrategies() ){
			strategy.report(  reportContext, event );
		}
		reportContext.nextStep();
	}
}
