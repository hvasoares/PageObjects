package com.github.pageobject.runner.resources;

import org.junit.runner.RunWith;

import com.github.pageobject.PageObjectBuilderFactory;
import com.github.pageobject.runner.PageObjectFactoryAware;
import com.github.pageobject.runner.PageObjectRunner;
import com.github.pageobject.runner.PageObjectTest;

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
