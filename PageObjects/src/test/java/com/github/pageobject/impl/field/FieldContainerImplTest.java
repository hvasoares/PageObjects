package com.github.pageobject.impl.field;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.impl.Field;

public class FieldContainerImplTest {

	private Mockery ctx;
	private FieldContainerImpl inst;
	private Field secondField;
	private Field firstField;

	@Before
	public void setUp() {
		ctx = new Mockery();
		firstField = ctx.mock(Field.class,"first field");
		secondField = ctx.mock(Field.class,"Second field");
		
		inst = new FieldContainerImpl();
	}
	
	@Test
	public void testShouldCallAField() {
		ctx.checking(new Expectations(){{
			oneOf(firstField).getAlias();
			will(returnValue("Not this"));
			oneOf(secondField).getAlias();
			will(returnValue("theOne"));
			oneOf(secondField).fill("someValue");
		}});
		inst.add(firstField);
		inst.add(secondField);
		inst.fill("theOne","someValue");
		
		ctx.assertIsSatisfied();
	}

	@Test(expected=RuntimeException.class)
	public void testShouldThrowException() {
		ctx.checking(new Expectations(){{
			oneOf(firstField).getAlias();
			will(returnValue("Not this"));
			oneOf(secondField).getAlias();
			will(returnValue("nether this"));
			oneOf(secondField).fill("someValue");
		}});
		inst.add(firstField);
		inst.add(secondField);
		inst.fill("theOne","someValue");
		
		ctx.assertIsSatisfied();
	}
	
}
