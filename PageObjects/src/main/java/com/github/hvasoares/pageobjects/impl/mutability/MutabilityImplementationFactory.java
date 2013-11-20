package com.github.hvasoares.pageobjects.impl.mutability;

import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

import static com.google.common.base.Preconditions.*;
public abstract class MutabilityImplementationFactory {
	private static PageContext context;

	public static ProxyStatePageObjectAdapter createStatePageObject(FieldFactory fieldFactory){
		return new ProxyStatePageObject(getContext(fieldFactory));
	}
	
	public static ProxyPageObjectBuilderAdapter createPageBuilder(FieldFactory fieldFactory){
		return new ProxyPageBuilder(getContext(fieldFactory));
	}
	

	private static PageContextI getContext(FieldFactory fieldFactory) {
		if(context!=null)
			return context;
		return context = new PageContext(fieldFactory);
	}
}
