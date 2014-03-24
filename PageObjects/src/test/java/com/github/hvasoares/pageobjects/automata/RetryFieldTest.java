package com.github.hvasoares.pageobjects.automata;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class RetryFieldTest {

	@Mock private TryField wontWork1;
	@Mock private TryField wontWork2;
	@Mock private TryField willWork;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	private RetryField instance;
	@Mock private WebDriver driver;
	private List<TryField> lists;

	@Test
	public void shouldFillAField() {
		instance = new RetryField(
				"alias", 
				"//someXpath", 
				lists = Arrays.asList(
						wontWork1,
						wontWork2,
						willWork
				)
		);
		
		ctx.checking(new Expectations(){{
			
			for(TryField f : lists)
				oneOf(f).setWebDriver(driver);
			
			for(TryField f : Arrays.asList(wontWork1,wontWork2)){
				oneOf(f).filled("//someXpath", "someValue");
				will(returnValue(false));
			}
			
			oneOf(willWork).filled("//someXpath", "someValue");
			will(returnValue(true));
		}});
		
		instance.setWebDriver(driver);
		instance.fill("someValue");
	}

}
