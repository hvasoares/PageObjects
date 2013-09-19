package com.github.pageobject.impl.mutability;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;

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
