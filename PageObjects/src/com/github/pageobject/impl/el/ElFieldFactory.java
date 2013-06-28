package com.github.pageobject.impl.el;

import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.Field;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.field.ClickableParams;

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

}
