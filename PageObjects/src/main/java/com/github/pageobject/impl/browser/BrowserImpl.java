package com.github.pageobject.impl.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserImpl implements Browser{

	private WebDriver driver;
	private boolean notStarted;
	private boolean closed;

	public BrowserImpl(WebDriver driver) {
		this.driver = driver;
		notStarted = true;
		closed=false;
	}

	@Override
	public void click(String xpath) {
		driver.findElement(By.xpath(xpath))
			.click();
	}

	@Override
	public void fill(String xpath, String value) {
		assertValidState();
		WebElement textF = driver.findElement(By.xpath(xpath));
		textF.sendKeys(value);
	}

	@Override
	public void goToStartUrl(String url) {
		driver.get(url);
		notStarted = false;
	}

	public void assertValidState() {
		if(notStarted)
			throw new RuntimeException("The browser is not started. Call goToStartUrl");
		if(closed)
			throw new RuntimeException("The browser is closed");
	}

	@Override
	public void close() {
		driver.close();
		driver.quit();
		closed=true;
	}
}
