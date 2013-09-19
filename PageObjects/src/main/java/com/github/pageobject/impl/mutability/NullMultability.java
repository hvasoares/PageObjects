package com.github.pageobject.impl.mutability;

import com.github.pageobject.Mutability;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

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

}
