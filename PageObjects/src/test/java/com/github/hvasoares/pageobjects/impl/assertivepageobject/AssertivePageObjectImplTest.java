package com.github.hvasoares.pageobjects.impl.assertivepageobject;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.IncompletePageObject;
import com.github.hvasoares.pageobjects.impl.assertivepageobject.AssertivenessImpl;

public class AssertivePageObjectImplTest {

	private Mockery ctx;
	private IncompletePageObject inner;
	private AssertivenessImpl inst;
	private WebDriver driver;

	@Before
	public void setUp() {
		ctx  = new Mockery();
		inst = new AssertivenessImpl(
				driver = ctx.mock(WebDriver.class)
		);
	}

	
	@Test(expected=NoSuchElementException.class)
	public void shouldTestSomeXpath(){
		
		
		ctx.checking(new Expectations(){{
			oneOf(driver).findElement(By.xpath("//someXpath"));
			will(throwException(new NoSuchElementException("some exception")));
		}});
		
		inst.pageAssertion("//someXpath");
		
		ctx.assertIsSatisfied();
	}	
	@Test(expected=NoSuchElementException.class)
	public void shouldSupportNamedAssertions(){
		ctx.checking(new Expectations(){{
			oneOf(driver).findElement(By.xpath("//someXpath"));
			will(throwException(new NoSuchElementException("some exception")));
		}});
		
		inst.addNamedAssertion("someName", "//someXpath");
		inst.checkAssertion("someName");
		
		ctx.assertIsSatisfied();
	}
}
