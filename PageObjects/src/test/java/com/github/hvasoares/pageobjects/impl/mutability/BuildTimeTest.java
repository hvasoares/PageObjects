package com.github.hvasoares.pageobjects.impl.mutability;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.mutability.BuildTime;
import com.github.hvasoares.pageobjects.impl.mutability.FluidXpathFactoryI;
import com.github.hvasoares.pageobjects.impl.mutability.FluidXpathI;
import com.github.hvasoares.pageobjects.impl.mutability.MutabilityContextI;

public class BuildTimeTest {

	
	@Mock private MutabilityContextI mutabilityCtx;
	@Mock private FluidXpathFactoryI clickableFactory;
	@Mock private FluidXpathI click;
	@Mock private FluidXpathI newClick;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	private BuildTime instance;

	@Test
	public void shouldCreateAClickable() {
		instance = new BuildTime(mutabilityCtx, clickableFactory);
		
		ctx .checking(new Expectations(){{
			oneOf(clickableFactory).create("//xpath:placeHolder");
			will(returnValue(click));
			oneOf(mutabilityCtx).add("someAlias",click);
		}});
		
		instance.add("someAlias", "//xpath:placeHolder");
	}
	@Test
	public void shouldCreateClickableWithPageTransition(){
		instance = new BuildTime(mutabilityCtx, clickableFactory);
		
		ctx .checking(new Expectations(){{
			oneOf(clickableFactory).create("//xpath:placeHolder");
			will(returnValue(click));
			oneOf(mutabilityCtx).add("someAlias",click,"toPage");
		}});
		
		instance.add("someAlias", "//xpath:placeHolder","toPage");
	}
	
	@Test
	public void shouldBeAbleToExtendSomeClickableThatHasTransition(){
		instance = new BuildTime(mutabilityCtx, clickableFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(mutabilityCtx).get("oldClickable");
			will(returnValue(click));
			
			oneOf(click).getXpath();
			will(returnValue("//xpath:placeHolder1,:placeHolder2,whatever"));
			
			oneOf(clickableFactory).create("//xpathvalueReplaced,:placeHolder2,whatever");
			will(returnValue(newClick));
			
			oneOf(mutabilityCtx).add("newClickable", newClick,"toPage");
		}});
		
		instance.extendsClickable(
				"from","oldClickable",
				"newName", "newClickable",
				"placeHolder1", "valueReplaced",
				"toPage"
		);
	}
	
	@Test
	public void shouldBeAbleToExtendSomeClickableThatHasNoTransition(){
		instance = new BuildTime(mutabilityCtx, clickableFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(mutabilityCtx).get("oldClickable");
			will(returnValue(click));
			
			oneOf(click).getXpath();
			will(returnValue("//xpath:placeHolder1,:placeHolder2,whatever"));
			
			oneOf(clickableFactory).create("//xpathvalueReplaced,:placeHolder2,whatever");
			will(returnValue(newClick));
			
			oneOf(mutabilityCtx).add("newClickable", newClick);
		}});
		
		instance.extendsClickable(
				"from","oldClickable",
				"newName", "newClickable",
				"placeHolder1", "valueReplaced"
		);
	}

}
