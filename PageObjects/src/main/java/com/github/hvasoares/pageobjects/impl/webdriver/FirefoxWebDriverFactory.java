package com.github.hvasoares.pageobjects.impl.webdriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.github.hvasoares.pageobjects.impl.field.file.PathGeneratorImpl;

public class FirefoxWebDriverFactory implements WebDriverFactory{

	private FirefoxDriver result;

	@Override
	public WebDriver create() {
		if(result!=null && result.toString()!=null)
			return result;
		FirefoxProfile profile = new FirefoxProfile();
		try {
			profile.addExtension(new File(
				new PathGeneratorImpl().generateFromResourceName(
					"firebug-1_11_4.xpi"
				)
			));
			profile.addExtension(new File(
					new PathGeneratorImpl().generateFromResourceName(
						"xpath_checker.0_4_4_fx.xpi"
					)
				));
			profile.setPreference("extensions.firebug.currentVersion", "2.0");
			profile.setPreference("extensions.firebug.addonBarOpened", true);
			profile.setPreference("extensions.firebug.console.enableSites", true);
			profile.setPreference("extensions.firebug.script.enableSites", true);
			profile.setPreference("extensions.firebug.net.enableSites", true);
			profile.setPreference("extensions.firebug.previousPlacement", 1);
			profile.setPreference("extensions.firebug.allPagesActivation", "on");
			profile.setPreference("extensions.firebug.onByDefault", true);
			profile.setPreference("extensions.firebug.defaultPanelName", "net");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		result = new FirefoxDriver(profile);
		result.manage().window().maximize();
		result.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
		return result;
	}

}
