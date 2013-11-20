package com.github.hvasoares.pageobjects.impl.el;

import org.apache.commons.jexl2.MapContext;

import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public class ElFactory {

	private static ElContextImpl elContext;

	public static FieldFactory createFieldFactory(
			FieldFactory innerFieldFactory) {
		return new ElFieldFactory(createElContext(), innerFieldFactory);
	}

	private static ElContextImpl createElContext() {
		if(elContext!=null)
			return elContext;
		elContext = new ElContextImpl(
				new MapContext(),
				new JexlExpressionFactoryImpl()
		);
		return elContext;
	}

	public static ProxyStatePageObjectAdapter createElContextStatePageObject() {
		return createElContext();
	}

}
