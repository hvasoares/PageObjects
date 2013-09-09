package com.github.pageobject.impl.field;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.file.FileFieldFactory;

public class FieldFactoryImpl implements FieldFactory{

	private Browser browser;
	private StatePageObject machine;
	private FileFieldFactory fileFieldFactory;

	public FieldFactoryImpl(Browser browser, StatePageObject machine,
			FileFieldFactory fileFieldFactory) {
		this.browser = browser;
		this.machine = machine;
		this.fileFieldFactory = fileFieldFactory;
	}

	@Override
	public Field createTextField(String alias, String xpath) {
		return new TextField(alias,xpath,browser);
	}

	@Override
	public Clickable createClickable(String aliase, String xpath,
			String toPageAlias) {
		return new ClickableImpl(
				new ClickableParams(aliase,xpath,toPageAlias),
				browser,
				machine
		);
	}

	@Override
	public Clickable createClickable(String alias, String xpath) {
		return new ClickableImpl(
				new ClickableParams(alias,xpath),
				browser,
				machine
		);
	}

	@Override
	public Field createFileField(String alias, String xpath) {
		return fileFieldFactory.create(alias,xpath);
	}
}