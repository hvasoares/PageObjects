package com.github.hvasoares.pageobjects.automata;

import java.util.Map;
import java.util.Map.Entry;

import com.github.hvasoares.pageobjects.AutomataFieldFiller;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.utils.Coolections;

public class AutomataFieldFillerImpl implements AutomataFieldFiller{

	private PageObjectBuilder builder;
	private RetryFieldFactory fieldFactory;

	public AutomataFieldFillerImpl(RetryFieldFactory retryFieldFactory) {
		this.fieldFactory = retryFieldFactory;
	}

	@Override
	public PageObjectBuilder addFieldXpathPair(String alias, String xpath) {
		builder.addCustomField(fieldFactory.create(alias, xpath));
		return builder;
	}

	@Override
	public PageObjectBuilder addFieldXpathPairs(String... args) {
		Map<String,String> pairs = Coolections.hashMap(args);
		for(Entry<String, String> e : pairs.entrySet())
			addFieldXpathPair(e.getKey(), e.getValue());
		return builder;
	}

	@Override
	public void setBuilder(PageObjectBuilder value) {
		this.builder  =value;
	}

}
