package com.github.pageobject.impl.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.github.pageobject.impl.field.file.PathGeneratorImpl;

public class FirefoxWebDriverFactory implements WebDriverFactory{

	@Override
	public WebDriver create() {
		FirefoxProfile profile = new FirefoxProfile();
		try {
			profile.addExtension(new File(
				new PathGeneratorImpl().generateFromResourceName(
					"firebug-1_11_4.xpi"
				)
			));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		FirefoxDriver result = new FirefoxDriver(profile);
		result.manage().window().maximize();
		result.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return result;
	}

}
