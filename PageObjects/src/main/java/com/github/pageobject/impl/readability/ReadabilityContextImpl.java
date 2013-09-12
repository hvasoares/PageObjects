package com.github.pageobject.impl.readability;

import java.util.HashMap;
import java.util.Map;

import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.Readability;

public class ReadabilityContextImpl extends ProxyStatePageObjectAdapter implements ReadabilityContext{

	private Map<String, Readability> db;

	public ReadabilityContextImpl(){
		this.db = new HashMap<String,Readability>();
	}
	
	@Override
	public void setState(String stateName) {
		setReadability(this.db.get(stateName));
		getInner().setState(stateName);
	}

	@Override
	public void add(String pageName, Readability current) {
		this.db.put(pageName, current);
	}

}
