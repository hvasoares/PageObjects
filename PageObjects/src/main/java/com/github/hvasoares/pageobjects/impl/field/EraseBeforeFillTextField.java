package com.github.hvasoares.pageobjects.impl.field;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.Keys;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Field;

public class EraseBeforeFillTextField implements Field{

	private TextField inner;
	private StatePageObject machine;

	public EraseBeforeFillTextField(TextField textField, StatePageObject machine) {
		this.inner = textField;
		this.machine = machine;
	}

	@Override
	public void fill(String value) {
		String currentString = machine.readability().read(getAlias());
		CharSequence [] backSpaces  = new CharSequence[currentString.length()]; 
		
		for(int i=0;i< backSpaces.length;i++)
			backSpaces[i]=Keys.BACK_SPACE;
		
		inner.fill(Keys.chord(backSpaces));
		inner.fill(value);
	}

	private <T> Set<Entry<Integer,T>> each(T[] split) {
		HashMap<Integer,T> result = new HashMap<>();
		for(int i=0; i< split.length; i++){
			result.put(i, split[i]);
		}
		return result.entrySet();
	}

	@Override
	public String getAlias() {
		return inner.getAlias();
	}

}
