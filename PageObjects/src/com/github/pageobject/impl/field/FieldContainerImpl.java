package com.github.pageobject.impl.field;

import java.util.HashMap;

import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.FieldContainer;

public class FieldContainerImpl implements FieldContainer{

	private HashMap<String, Field> dataBase;

	public FieldContainerImpl(){
		this.dataBase = new HashMap<String,Field>();
	}
	
	@Override
	public void fill(String aliase, String value) {
		Field chosen = dataBase.get(aliase);
		if(chosen==null)
			throw new RuntimeException("there's no field aliased with " + aliase);
		chosen.fill(value);
	}

	@Override
	public void add(Field field) {
		dataBase.put(field.getAlias(),field);
	}
	
}