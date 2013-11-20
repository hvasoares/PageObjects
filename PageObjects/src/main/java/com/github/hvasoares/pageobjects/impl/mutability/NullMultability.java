package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.List;

import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.MutabilityCustomFieldFactory;
import com.github.hvasoares.pageobjects.MutableAssertiveness;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.StatePageObject;

public class NullMultability implements Mutability{

	private String pagename;
	
	public NullMultability(String pagename) {
		this.pagename = pagename;
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath,
			String pageToTransitioning) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public PageObjectBuilder addClickable(String alias, String xpath) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public PageObjectBuilder extendsClickable(String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public StatePageObject click(String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public PageObjectBuilder addReadProperty(String alias, String xpath) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}
	
	@Override
	public MutableAssertiveness assertiveness(){
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	public List<String> readAsList(String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public StatePageObject doubleClick(String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public String read(String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public PageObjectBuilder addCustomField(String alias, String xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public StatePageObject fill(String alias, String value, String... args) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

	@Override
	public PageObjectBuilder addCustomField(String alias, List<String> xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory) {
		throw new RuntimeException("There's no mutability named '"+pagename+"'");
	}

}
