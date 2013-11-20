package com.github.pageobject.impl.mutability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.assertivepageobject.AssertivenessFactory;

public class MutableAssertivenessImplTest {

	@Mock private WebDriver webdriver;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private PageObjectBuilder pageObjectBuilder;
	@Mock private StatePageObject statePageObject;
	
	@Test
	public void shouldAssert() {
		AssertivenessFactory.create(webdriver);
		
		MutableAssertivenessImpl instance = new MutableAssertivenessImpl();
		
		instance.setPageObjectBuilder(pageObjectBuilder);
		instance.setStatePageObject(statePageObject);
		
		assertEquals(
			instance.add("someAlias", "//someXpath[:placeHolder1=:placeHolder2]"),
			pageObjectBuilder
		);
		
		ctx.checking(new Expectations(){{
			oneOf(webdriver).findElement(By.xpath("//someXpath[value1=value2]"));
		}});
		
		assertEquals(
			instance.check("someAlias",
					"placeHolder1",	"value1",
					"placeHolder2",	"value2"
			),
			statePageObject
		);
	}

}
