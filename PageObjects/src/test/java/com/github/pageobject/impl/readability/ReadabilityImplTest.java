package com.github.pageobject.impl.readability;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.pageobject.impl.Readability;

public class ReadabilityImplTest {

	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private WebDriver webDriver;
	@Mock private WebElement element;
	private Readability instance;

	@Test
	public void shouldGetTheInnerTextOfSomeElement() {
		instance = new ReadabilityImpl(webDriver);
		ctx.checking(new Expectations(){{
			oneOf(webDriver).findElement(By.xpath("//someXpath"));
			will(returnValue(element));
			
			oneOf(element).getText();
			will(returnValue("valueOfProperty"));
		}});
		
		instance.setProperty("someProperty","//someXpath");
		assertEquals(instance.read("someProperty"),"valueOfProperty");
	}
	@Test
	public void shouldGetTheInnerValueOfSomeElementIfItHasntAText() {
		instance = new ReadabilityImpl(webDriver);
		ctx.checking(new Expectations(){{
			exactly(2).of(webDriver).findElement(By.xpath("//someXpath"));
			will(returnValue(element));
			
			oneOf(element).getText();
			will(returnValue(null));
			
			oneOf(element).getAttribute("value");
			will(returnValue("valueOfPropertyFromAttributeValue"));
			
		}});
		
		instance.setProperty("someProperty","//someXpath");
		assertEquals(instance.read("someProperty"),"valueOfPropertyFromAttributeValue");
	}

}
