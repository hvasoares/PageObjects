package com.github.pageobject.impl.field.file;

import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.browser.Browser;

public class FileFieldFactoryImpl implements FileFieldFactory {

	private Browser browser;
	private PathGenerator pathGenerator;

	public FileFieldFactoryImpl(Browser browser) {
		this.browser = browser;
		pathGenerator = new PathGeneratorImpl();
	}

	@Override
	public Field create(String alias, String xpath) {
		return new FileInputField(alias,xpath,browser,pathGenerator);
	}

}
