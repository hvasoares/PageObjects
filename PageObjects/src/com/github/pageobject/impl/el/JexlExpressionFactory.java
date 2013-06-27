package com.github.pageobject.impl.el;

import org.apache.commons.jexl2.JexlContext;

public interface JexlExpressionFactory {

	String getResult(JexlContext mapContext, String string);

}
