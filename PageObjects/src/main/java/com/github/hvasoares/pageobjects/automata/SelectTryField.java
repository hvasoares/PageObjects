package com.github.hvasoares.pageobjects.automata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.Select;
import com.github.hvasoares.pageobjects.impl.field.select.SelectField;

public class SelectTryField implements TryField{

	private Browser browser;
	private WebDriver driver;

	@Override
	public boolean filled(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		
		if(!element.getTagName().equalsIgnoreCase("select"))
			return false;
		
		Select field = SelectField.createFluidSelect(null, xpath);
		field.setBrowser(browser);
		field.fill(value);
		
		return true;
	}
	
	@Override
	public void setWebDriver(WebDriver value) {
		driver = value;
	}

	@Override
	public void setBrowser(Browser value) {
		browser = value;
	}

}
