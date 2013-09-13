package com.github.pageobject.impl.readability;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.Readability;

import static com.google.common.base.Preconditions.*;
public class ReadabilityContextImpl extends ProxyStatePageObjectAdapter implements ReadabilityContext{

	private Map<String, Readability> db;

	public ReadabilityContextImpl(){
		this.db = new HashMap<String,Readability>();
	}
	
	@Override
	public void setState(String stateName) {
		getInner().setState(stateName);
		setReadability(checkNotNull(this.db.get(stateName)));
	}

	@Override
	public void add(String pageName, Readability current) {
		this.db.put(pageName, current);
	}

}
