package com.github.hvasoares.pageobjects.automata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.file.FileFieldFactoryImpl;

public class FileTryField implements TryField {

	private WebDriver driver;
	private Browser browser;
	private FileFieldFactoryImpl factory;

	@Override
	public void setWebDriver(WebDriver value) {
		this.driver = value;
	}

	@Override
	public boolean filled(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		
		boolean isFileInput = element.getTagName().equalsIgnoreCase("input") &&
				element.getAttribute("type").equalsIgnoreCase("file");
		if(isFileInput)
			return false;
		
		factory =new FileFieldFactoryImpl(browser);
		factory.create(null, xpath).fill(value);
		
		return true;
	}

	@Override
	public void setBrowser(Browser value) {
		browser = value;
	}

}
