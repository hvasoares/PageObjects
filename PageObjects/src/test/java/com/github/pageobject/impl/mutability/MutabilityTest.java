package com.github.pageobject.impl.mutability;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.internal.seleniumemulation.Click;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public class MutabilityTest {

	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	private Mutability instance;
	@Mock private BuildTimeI buildTime;
	@Mock private PageObjectBuilder pageObjB;
	@Mock private ExecutionTimeI executionTime;
	@Mock private StatePageObject stateObj;
	

	@Test
	public void shouldGenerateClickablesGivenPlaceholders() {
		
		instance = new Mutability(buildTime,executionTime);
		
		ctx.checking(new Expectations(){{
			oneOf(buildTime).add("someAlias","//:placeHolder","toPage");
			
			allowing(buildTime).getPageObjectBuilder();
			will(returnValue(pageObjB));
			
			oneOf(buildTime).add("anotherAlias","//:anotherPlaceHolder");

		}});
		
		assertEquals(
				instance.addClickable("someAlias", "//:placeHolder","toPage"),
				pageObjB
		);
		
		assertEquals(
				instance.addClickable("anotherAlias", "//:anotherPlaceHolder"),
				pageObjB
		);
	}
	
	@Test
	public void shouldExtendClickables(){
		instance = new Mutability(buildTime, executionTime);
		ctx.checking(new Expectations(){{
			oneOf(buildTime).extendsClickable(
					"someAlias",
					"newName","someNewName",
					"placeHolder","placeHolderValue",
					"newPageTransitioning"
				);
			oneOf(buildTime).getPageObjectBuilder();
			will(returnValue(pageObjB));
		}});
		
		assertEquals(
				instance.extendsClickable(
						"someAlias",
						"newName","someNewName",
						"placeHolder","placeHolderValue",
						"newPageTransitioning"
				),
				pageObjB
		);
		
	}
	
	@Test
	public void shouldClick(){
		instance = new Mutability(buildTime, executionTime);
		
		ctx.checking(new Expectations(){{
			oneOf(executionTime).getStatePageObject();
			will(returnValue(stateObj));
			oneOf(executionTime).click("someAlias","placeholder","value");
		}});
		
		assertEquals(
				instance.click("someAlias", "placeholder", "value"),
				stateObj
		);
	}

}
