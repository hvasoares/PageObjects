package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;

public class ProxyPageBuilder extends ProxyPageObjectBuilderAdapter {

	private PageContextI pageContext;
	
	public ProxyPageBuilder(PageContextI context) {
		pageContext = context;
	}

	@Override
	public PageObjectBuilder setName(String value) {
		setMutability(pageContext.createMutability(value,getOuter()));
		return super.setName(value);
	}
	
}
