package com.github.hvasoares.pageobjects;

import org.openqa.selenium.WebDriver;

public final class WebDriverHolder {

	private static WebDriver webDriver = null;
	
	public static void value( WebDriver wd ){
		if ( wd == null ){
			throw new IllegalStateException( WebDriverHolder.class.getSimpleName() + " Não contém uma referência válida de " + WebDriver.class.getName() );
		}
		webDriver=wd;
	}
	
	public static WebDriver getWebDriver(){
		return webDriver;
	}
}
