package com.github.pageobject.impl.field;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.github.pageobject.impl.Clickable;

public class ClickableContainerImplTest {

	private Mockery ctx;
	private Clickable click1;
	private Clickable click2;
	private ClickableContainerImpl inst;

	@Before
	public void setUp() {
		ctx = new Mockery();
		click1 = ctx.mock(Clickable.class,"click1");
		click2 = ctx.mock(Clickable.class,"click2");
		
		inst = new ClickableContainerImpl();
	}
	
	@Test
	public void shouldClick() {
		ctx.checking(new Expectations(){{
			oneOf(click1).getAlias();
			will(returnValue("not this"));
			oneOf(click2).getAlias();
			will(returnValue("theOne"));
			oneOf(click2).click();
		}});
		
		inst.add(click1);
		inst.add(click2);
		
		inst.click("theOne");
		ctx.assertIsSatisfied();
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionIfTheClickableNotFound(){
		ctx.checking(new Expectations(){{
			oneOf(click1).getAlias();
			will(returnValue("not this"));
			oneOf(click2).getAlias();
			will(returnValue("neither this"));
		}});
		
		inst.add(click1);
		inst.add(click2);
		
		inst.click("theOne");
		ctx.assertIsSatisfied();
	}
}
