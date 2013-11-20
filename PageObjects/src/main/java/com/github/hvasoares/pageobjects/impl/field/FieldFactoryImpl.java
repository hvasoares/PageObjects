package com.github.hvasoares.pageobjects.impl.field;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.file.FileFieldFactory;

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
		return new EraseBeforeFillTextField(
				new TextField(alias,xpath,browser),
				machine
		);
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
	
	@Override
	public Field createCustomField(CustomField custom){
		custom.setBrowser(browser);
		return custom;
	}
}