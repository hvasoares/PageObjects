package com.github.hvasoares.pageobjects.automata;

import java.util.Arrays;
import java.util.List;

import com.github.hvasoares.pageobjects.impl.field.CustomField;

public class RetryFieldFactoryImpl implements RetryFieldFactory{

	private List<TryField> tryFields;

	public RetryFieldFactoryImpl() {
		tryFields = Arrays.asList(
				new SelectTryField(),
				new TextTryField(),
				new FileTryField()
		);
	}

	@Override
	public CustomField create(String alias, String xpath) {
		return new RetryField(
				alias,
				xpath,
				tryFields
			);
	}

	public void addTryField(TryField value){
		tryFields.add(value);
	}
}
