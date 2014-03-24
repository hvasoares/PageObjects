package com.github.hvasoares.pageobjects.automata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.TextField;

public class TextTryField implements TryField {

	private Browser browser;
	private WebDriver driver;

	@Override
	public void setWebDriver(WebDriver value) {
		driver = value;
	}

	@Override
	public boolean filled(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		
		boolean inputText = element.getTagName().equalsIgnoreCase("input") && (
				element.getAttribute("type").equalsIgnoreCase("password")
				|| element.getAttribute("type").equalsIgnoreCase("text")
		);
		
		boolean textArea  = element.getTagName().equalsIgnoreCase("textarea");
				
		if(!inputText && !textArea)
			return false;
		
		TextField field = new TextField(null, xpath, browser);
		field.fill(value);
		return true;
	}

	@Override
	public void setBrowser(Browser value) {
		browser = value;
	}

}
