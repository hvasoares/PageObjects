package com.github.hvasoares.pageobjects.impl.webdriver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxProfile;

import com.github.hvasoares.pageobjects.impl.field.file.PathGeneratorImpl;

public class FirefoxProfileFactory implements ProfileFactory {

	@Override
	public Object create() {
		FirefoxProfile  profile = new FirefoxProfile();
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
		}catch ( IOException e ){
			throw new RuntimeException( e );
		}
		return profile;
	}

}
