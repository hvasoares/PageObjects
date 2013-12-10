package com.github.hvasoares.pageobjects;

import org.openqa.selenium.WebDriver;

public final class WebDriverHolder {

	private static WebDriver webDriver = null;
	
	public static void value( WebDriver wd ){
		webDriver = wd;
	}
	
	public static WebDriver getWebDriver(){
		if ( webDriver == null ){
			throw new IllegalStateException( WebDriverHolder.class.getSimpleName() + " n�o cont�m uma refer�ncia v�lida de " + WebDriver.class.getName() );
		}
		return webDriver;
	}
}
