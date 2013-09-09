package com.github.pageobject.impl.field;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.file.FileFieldFactory;

public class FieldFactoryImplTest {

	private Mockery ctx;
	private FieldFactoryImpl inst;
	private Browser browser;
	private StatePageObject machine;
	private FileFieldFactory fileFieldFactory;

	@Before
	public void setUp() {
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		machine = ctx.mock(StatePageObject.class);
		fileFieldFactory = ctx.mock(FileFieldFactory.class);
		inst = new FieldFactoryImpl(browser,machine,fileFieldFactory);
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

	@Test
	public void shouldDelegateFileFieldCreationingToItsFactory(){
		final Field fileField = ctx.mock(Field.class);
		ctx.checking(new Expectations(){{
			oneOf(fileFieldFactory).create("someAlias","someXpath");
			will(returnValue(fileField));
		}});
		Field result = inst.createFileField("someAlias","someXpath");
		ctx.assertIsSatisfied();
		assertEquals(result,fileField);
	}

}
