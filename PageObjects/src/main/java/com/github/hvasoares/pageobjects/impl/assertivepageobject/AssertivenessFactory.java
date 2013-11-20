package com.github.hvasoares.pageobjects.impl.assertivepageobject;

import org.openqa.selenium.WebDriver;
import static com.google.common.base.Preconditions.checkState;
public abstract class AssertivenessFactory {

	private static WebDriver webdriver;

	public static Assertiveness create(WebDriver driver){
		webdriver = driver;
		return new AssertivenessImpl(driver);
	}
	
	public static Assertiveness create(){
		checkState(webdriver!=null,"Webdriver is null! Are you calling this method before DefaultFactory?");
		return create(webdriver);
	}
}
