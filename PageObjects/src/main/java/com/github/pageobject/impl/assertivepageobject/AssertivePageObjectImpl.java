package com.github.pageobject.impl.assertivepageobject;

import java.util.HashMap;
import java.util.Map;

import lombok.Delegate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.IncompletePageObject;

public class AssertivePageObjectImpl implements AssertivePageObject{
	@Delegate(types=IncompletePageObject.class)
	private IncompletePageObject inner;
	private WebDriver driver;
	private Map<String,String> db;
	
	public AssertivePageObjectImpl(IncompletePageObject inner, WebDriver webDriver){
		this.inner = inner;
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
