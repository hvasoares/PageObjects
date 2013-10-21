package com.github.pageobject.impl.field.file;

import static com.google.common.base.Preconditions.checkNotNull;

import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.CustomField;

public class FileFieldFactoryImpl implements FileFieldFactory {

	private Browser browser;
	private PathGenerator pathGenerator;

	public FileFieldFactoryImpl(Browser browser) {
		this();
		this.browser = browser;
	}

	public FileFieldFactoryImpl() {
		pathGenerator = new PathGeneratorImpl();
	}

	@Override
	public CustomField create(String alias, String xpath) {
		return new FileInputField(alias,xpath,browser,pathGenerator);
	}

	@Override
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

}
