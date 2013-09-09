package com.github.pageobject.impl.browser;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

public class RetryBrowserTest {

	private Mockery ctx;
	private Browser inner;
	private RetryBrowser inst;

	@Test
	public void shouldRetryFillAfterATimeOut() {
		ctx = new Mockery();
		inner = ctx.mock(Browser.class);
		inst = new RetryBrowser(inner);
		
		ctx.checking(new Expectations(){{
			oneOf(inner).fill("someXpath","someValue");
			will(throwException(new NoSuchElementException("blah")));
			oneOf(inner).fill("someXpath","someValue");
		}});
		
		inst.setTimeout(1);
		inst.fill("someXpath","someValue");
		ctx.assertIsSatisfied();
	}

}
