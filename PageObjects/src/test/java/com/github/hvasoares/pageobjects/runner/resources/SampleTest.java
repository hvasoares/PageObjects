package com.github.hvasoares.pageobjects.runner.resources;

import org.junit.runner.RunWith;

import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.runner.PageObjectFactoryAware;
import com.github.hvasoares.pageobjects.runner.PageObjectRunner;
import com.github.hvasoares.pageobjects.runner.PageObjectTest;

@RunWith(PageObjectRunner.class)
@PageObjectTest(repository=SampleRepository.class)
public class SampleTest {

	private boolean tested=false;
	private boolean injected = false;;
	
	public boolean isInjected() {
		return injected;
	}

	public boolean isTested() {
		return tested;
	}

	@PageObjectFactoryAware
	public void setFactory(PageObjectBuilderFactory factory){
		injected  = true;
	}
	
	public void testMethod(){
		tested = true;
	}
}
