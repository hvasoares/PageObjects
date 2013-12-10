package com.github.hvasoares.pageobjects.report;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;
import com.github.hvasoares.pageobjects.proxy.MatryoshkaDollFactory;

public class ReportedStatePageObjectTest {

	private ReportedStatePageObject instance;
	@Mock private StatePageObject statePageObject;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{setImposteriser(ClassImposteriser.INSTANCE);}};
	@Mock private ReportContextI reportContext;
	@Mock private PageLoadedWait pageLoadedWait;

	@Test
	public void shouldClick() {
		instance = new ReportedStatePageObject(reportContext);
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> md = new MatryoshkaDollFactory<>();
		StatePageObject metaInstance = md.create(statePageObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(reportContext).tryReportEvent(UserEvents.CLICK);
			oneOf(statePageObject).click("alias");
		}});

		metaInstance.click("alias");
	}

	@Test
	public void shouldDoubleClick() {
		instance = new ReportedStatePageObject(reportContext);
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> md = new MatryoshkaDollFactory<>();
		StatePageObject metaInstance = md.create(statePageObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(reportContext).tryReportEvent(UserEvents.DOUBLE_CLICK);
			oneOf(statePageObject).doubleClick("alias");
		}});

		metaInstance.doubleClick("alias");
	}
	
	@Test
	public void shouldFill() {
		instance = new ReportedStatePageObject(reportContext);
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> md = new MatryoshkaDollFactory<>();
		StatePageObject metaInstance = md.create(statePageObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(reportContext).tryReportEvent(UserEvents.FILL);
			oneOf(statePageObject).fill("field","value");
		}});

		metaInstance.fill("field","value");
	}
	@Test
	public void shouldSetState() {
		instance = new ReportedStatePageObject(reportContext,pageLoadedWait);
		MatryoshkaDollFactory<StatePageObject, ProxyStatePageObjectAdapter> md = new MatryoshkaDollFactory<>();
		StatePageObject metaInstance = md.create(statePageObject, instance);
		
		ctx.checking(new Expectations(){{
			oneOf(reportContext).tryReportEvent(UserEvents.CHANGE_PAGE);
			oneOf(pageLoadedWait).waitForPageLoaded();
			oneOf(statePageObject).setState("someState");
		}});

		metaInstance.setState("someState");
	}	
}
