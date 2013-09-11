package com.github.pageobject.impl.assertivepageobject;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AssertivePageObjectImpl implements Assertiveness{
	private WebDriver driver;
	private Map<String,String> db;
	
	public AssertivePageObjectImpl(WebDriver webDriver){
		this.driver = webDriver;
		this.db=new HashMap<String, String>();
	}

	@Override
	public void pageAssertion(String xpath) {
		driver.findElements(By.xpath(xpath));
	}

	@Override
	public void addNamedAssertion(String name, String xpath) {
		db.put(name,xpath);
	}

	@Override
	public void checkAssertion(String namedAssertion) {
		pageAssertion(db.get(namedAssertion));
	}
}
