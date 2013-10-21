package com.github.pageobject.impl.field;

import org.openqa.selenium.WebDriver;

import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.FieldFactory;

public class WebDriverAwareCustomFieldFactory implements FieldFactory{

	private FieldFactory inner;
	private WebDriver webDriver;

	public WebDriverAwareCustomFieldFactory(FieldFactory inner,
			WebDriver webDriver) {
		this.inner = inner;
		this.webDriver = webDriver;
	}
	public Field createCustomField(CustomField custom) {
		if(custom instanceof WebDriverAwareCustomField){
			WebDriverAwareCustomField webDriverField = (WebDriverAwareCustomField) custom;
			webDriverField.setWebDriver(webDriver);
		}
		return inner.createCustomField(custom);
	}

	public Field createTextField(String alias, String xpath) {
		return inner.createTextField(alias, xpath);
	}

	public Clickable createClickable(String alias, String xpath,
			String toPageAlias) {
		return inner.createClickable(alias, xpath, toPageAlias);
	}

	public Clickable createClickable(String alias, String xpath) {
		return inner.createClickable(alias, xpath);
	}

	public Field createFileField(String value, String xpath) {
		return inner.createFileField(value, xpath);
	}
}
