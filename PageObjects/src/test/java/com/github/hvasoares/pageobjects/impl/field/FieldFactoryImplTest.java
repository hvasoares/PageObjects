package com.github.hvasoares.pageobjects.impl.field;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.ClickableImpl;
import com.github.hvasoares.pageobjects.impl.field.FieldFactoryImpl;
import com.github.hvasoares.pageobjects.impl.field.TextField;
import com.github.hvasoares.pageobjects.impl.field.file.FileFieldFactory;

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
		assertTrue(inst.createTextField("field","xpath") instanceof EraseBeforeFillTextField);
	}

	@Test
	public void shouldDelegateFileFieldCreationingToItsFactory(){
		final CustomField fileField = ctx.mock(CustomField.class);
		ctx.checking(new Expectations(){{
			oneOf(fileFieldFactory).create("someAlias","someXpath");
			will(returnValue(fileField));
		}});
		Field result = inst.createFileField("someAlias","someXpath");
		ctx.assertIsSatisfied();
		assertEquals(result,fileField);
	}

}
