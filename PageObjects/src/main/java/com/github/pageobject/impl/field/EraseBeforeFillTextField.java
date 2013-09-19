package com.github.pageobject.impl.field;

import org.openqa.selenium.Keys;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Field;

public class EraseBeforeFillTextField implements Field{

	private TextField inner;
	private StatePageObject machine;

	public EraseBeforeFillTextField(TextField textField, StatePageObject machine) {
		this.inner = textField;
		this.machine = machine;
	}

	@Override
	public void fill(String value) {
		while(!"".equals(machine
				.readability()
				.read(getAlias())
		)){
			inner.fill(Keys.chord(Keys.BACK_SPACE));
		}
		inner.fill(value);
	}

	@Override
	public String getAlias() {
		return inner.getAlias();
	}

}
