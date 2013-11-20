package com.github.hvasoares.pageobjects.impl;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.impl.StatePageObjectImpl;
import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

public class StatePageObjectImplTest {

	private PageObject page;
	private Mockery ctx;
	private StatePageObjectImpl inst;

	@Test
	public void shouldReceiveNewPages() {
		ctx = new Mockery();
		page = ctx.mock(PageObject.class);
		final PageObjectRepository repository = ctx.mock(PageObjectRepository.class);
		ctx.checking(new Expectations(){{
			oneOf(repository).getPages();
			will(returnValue(Arrays.asList(new PageObject[]{page})));
			
			oneOf(page).getName();
			will(returnValue("someName"));
			
			oneOf(page).click("button");
			will(returnValue(page));
			oneOf(page).fill("field","value");
		}});
		
		
		inst = new StatePageObjectImpl(repository );
		
		inst.setState("someName");
		
		inst.click("button")
			.fill("field","value");
		
		ctx.assertIsSatisfied();
	}

}
