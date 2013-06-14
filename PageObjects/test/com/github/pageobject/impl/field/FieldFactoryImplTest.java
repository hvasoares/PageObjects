package com.github.pageobject.impl.field;

import static org.junit.Assert.assertTrue;

import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.browser.Browser;

public class FieldFactoryImplTest {

	private Mockery ctx;
	private FieldFactoryImpl inst;
	private Browser browser;
	private StatePageObject machine;

	@Before
	public void setUp() {
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		machine = ctx.mock(StatePageObject.class);
		inst = new FieldFactoryImpl(browser,machine);
	}

	@Test
	public void shouldCreateAClickAble() {
		assertTrue(inst.createClickable("field","xpath") instanceof ClickableImpl);
		assertTrue(inst.createClickable("field","xpath","page") instanceof ClickableImpl);
	}
	
	@Test
	public void shouldCreateTextfields(){
		assertTrue(inst.createTextField("field","xpath") instanceof TextField);
	}

}
