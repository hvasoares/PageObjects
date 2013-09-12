package com.github.pageobject.impl.el;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.JexlContext;

import com.github.pageobject.PageObject;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.StatePageObjectImpl;
import com.github.pageobject.impl.StatePageObjectSymbolTable;

public class ElContextImpl extends ProxyStatePageObjectAdapter implements ElContext {
	
	private JexlContext jexlContext;
	private JexlExpressionFactory expressionFactory;
	private Map<String,Map<String,String>> statePageMaps;
	private String current;
	
	public ElContextImpl(JexlContext jexlContext,
			JexlExpressionFactory expressionFactory) {
		this.jexlContext = jexlContext;
		this.expressionFactory = expressionFactory;
		statePageMaps = new HashMap<String, Map<String,String>>();
	}

	@Override
	public void setState(String stateName) {
		getInner().setState(stateName);
		setCurrentName(stateName);
		jexlContext.set(stateName,getCurrentMap());
	}
	
	public Map<String,String> getCurrentMap() {
		return this.statePageMaps.get(this.current);
	}

	private void setCurrentName(String stateName) {
		this.current = stateName;
		this.statePageMaps.put(
				this.current,
				new HashMap<String,String>()
		);
	}

	@Override
	public void assign(String var, String value) {
		getCurrentMap().put(var,value);
	}

	@Override
	public String evaluate(String expression) {
		return expressionFactory.getResult(jexlContext,expression);
	}
	

	public StatePageObject fill(String field, String value) { 
		assign(field,evaluate(value));
		return getInner().fill(field,evaluate(value));
	}
	
}
