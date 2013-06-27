package com.github.pageobject.impl.el;

import static org.junit.Assert.assertEquals;

import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.impl.FieldFactory;

public class ElFieldFactoryTest {

	private Mockery ctx;
	private FieldFactory innerFactory;
	private ElFieldFactory inst;
	private TestableElField result;
	private ElContext elContext;
	@Before
	public void setUp() {
		ctx = new Mockery();
		elContext = ctx.mock(ElContext.class);
		innerFactory = ctx.mock(FieldFactory.class);
		inst = new ElFieldFactory(elContext,innerFactory);
	}
	@Test
	public void shouldCreateElFieldWithContext() {		
		result =(TestableElField)inst.createTextField(
				"${someBeanHere}",
				"//xpathWith${otherBean}"
		);
		
		assertEquals(result.getAlias(),"${someBeanHere}");
		assertEquals(result.getXpath(),"//xpathWith${otherBean}");
		assertEquals(result.getElContext(),elContext);
		assertEquals(result.getFieldFactory(),innerFactory);
	}
	
	@Test
	public void shouldCreateElClickables(){
		TestableElClickable click = (TestableElClickable) inst.createClickable(
				"${someBeanHere}",
				"//xpathWith${otherBeanHere}"
		);
		assertEquals(click.getAlias(),"${someBeanHere}");
		assertEquals(click.getXpath(),"//xpathWith${otherBeanHere}");
		assertEquals(click.getElContext(),elContext);
		assertEquals(click.getFieldFactory(),innerFactory);
		
		TestableElClickable click2 = (TestableElClickable) inst.createClickable(
				"${someBeanHere}",
				"//xpathWith${otherBeanHere}",
				"toPage${someExpression}"
		);
		
		assertEquals(click2.getAlias(),"${someBeanHere}");
		assertEquals(click2.getXpath(),"//xpathWith${otherBeanHere}");
		assertEquals(click2.getToPageAlias(),"toPage${someExpression}");
		assertEquals(click2.getElContext(),elContext);
		assertEquals(click2.getFieldFactory(),innerFactory);
	}

}
