package com.github.hvasoares.pageobjects.impl.el;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.field.ClickableParams;
import com.github.hvasoares.pageobjects.impl.field.CustomField;

public class ElFieldFactory implements FieldFactory{
	private ElContext elContext;
	
	private FieldFactory fieldFactory;
	public ElFieldFactory(ElContext elContext, FieldFactory fieldFactory) {
		super();
		this.elContext = elContext;
		this.fieldFactory = fieldFactory;
	}

	@Override
	public Field createTextField(String alias, String xpath) {
		return new ElFieldImpl(elContext,fieldFactory,alias,xpath);
	}

	@Override
	public Clickable createClickable(String alias, String xpath,
			String toPageAlias) {
		return new ElClickable(
				new ClickableParams(alias,xpath,toPageAlias),
				elContext,
				fieldFactory
		);
	}

	@Override
	public Clickable createClickable(String alias, String xpath) {
		return createClickable(alias,xpath,null);
	}

	@Override
	public Field createFileField(String alias, String xpath) {
		return fieldFactory.createFileField(alias,xpath);
	}
	
	public Field createCustomField(CustomField field){
		return fieldFactory.createCustomField(field);
	}

}
