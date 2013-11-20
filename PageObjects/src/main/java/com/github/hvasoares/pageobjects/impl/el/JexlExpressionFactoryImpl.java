package com.github.hvasoares.pageobjects.impl.el;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.UnifiedJEXL;

public class JexlExpressionFactoryImpl implements JexlExpressionFactory{
	private UnifiedJEXL expressionLanguage;
	
	public JexlExpressionFactoryImpl(){
		expressionLanguage = new UnifiedJEXL(new JexlEngine());
	}
	
	@Override
	public String getResult(JexlContext mapContext, String expression) {
		return (String) expressionLanguage
				.parse(expression)
				.evaluate(mapContext);
	}

}
