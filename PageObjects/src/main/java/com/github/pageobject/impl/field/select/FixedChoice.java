package com.github.pageobject.impl.field.select;

import java.util.Arrays;
import java.util.List;

import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.Select;

import static com.google.common.base.Preconditions.*;
public class FixedChoice implements Select{

	private List<String> options;
	private Select select;

	public FixedChoice(Select innerSelect,String ... possibleOptions) {
		checkArgument(possibleOptions.length>=1,"Wrong number of parameters, "
				+ "parameters should be more than one possible values to select options");
		this.options = Arrays.asList(possibleOptions);
		this.select = innerSelect;
	}

	@Override
	public void fill(String value) {
		checkArgument(options.contains(value),"The " +value + " isnt configured in options.");
		select.fill(value);
	}

	@Override
	public String getAlias() {
		return select.getAlias();
	}

	@Override
	public void setBrowser(Browser value) {
		this.select.setBrowser(value);
	}

}
