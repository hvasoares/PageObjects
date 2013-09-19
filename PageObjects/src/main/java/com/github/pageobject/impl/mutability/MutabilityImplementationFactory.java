package com.github.pageobject.impl.mutability;

import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.ProxyPageObjectBuilderAdapter;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
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
