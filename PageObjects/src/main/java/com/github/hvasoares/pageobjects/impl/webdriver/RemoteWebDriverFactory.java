package com.github.hvasoares.pageobjects.impl.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverFactory implements WebDriverFactory {
	private RemoteWebDriver result;
	
	private ProfileFactory profileFactory;
	
	public RemoteWebDriverFactory(ProfileFactory profileFactory){
		this.profileFactory = profileFactory;
	}

	@Override
	public WebDriver create() {		
		if(result!=null && result.toString()!=null)
			return result;
		 
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability( FirefoxDriver.PROFILE , profileFactory.create() );
		result = new RemoteWebDriver( dc );
		result.manage().window().maximize();
		result.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return result;
	}
}
