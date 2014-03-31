package com.github.hvasoares.pageobjects.automata;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;

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
			exactly(2).of(driver).manage();
			Options options=ctx.mock(Options.class);
			will(returnValue(options));
			
			exactly(2).of(options).timeouts();
			Timeouts timeout = ctx.mock(Timeouts.class);
			will(returnValue(timeout ));
			
			oneOf(timeout).implicitlyWait(3, TimeUnit.SECONDS);
			
			for(TryField f : lists)
				oneOf(f).setWebDriver(driver);
			
			for(TryField f : Arrays.asList(wontWork1,wontWork2)){
				oneOf(f).filled("//someXpath", "someValue");
				will(returnValue(false));
			}
			
			oneOf(willWork).filled("//someXpath", "someValue");
			will(returnValue(true));
			
			oneOf(timeout).implicitlyWait(20, TimeUnit.SECONDS);
		}});
		
		instance.setWebDriver(driver);
		instance.fill("someValue");
	}

}
