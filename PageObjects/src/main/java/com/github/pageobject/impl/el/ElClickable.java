package com.github.pageobject.impl.el;

import com.github.pageobject.impl.Clickable;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.field.ClickableParams;

public class ElClickable implements TestableElClickable {
	private ClickableParams clickableParams;
	private ElContext elContext;
	private FieldFactory fieldFactory;
	public ElClickable(ClickableParams clickableParams, ElContext elContext,
			FieldFactory fieldFactory) {
		super();
		this.clickableParams = clickableParams;
		this.elContext = elContext;
		this.fieldFactory = fieldFactory;
	}

	@Override
	public void click() {
		generateClickable().click();
	}

	private Clickable generateClickable() {
		Clickable clickable;
		if(getToPageAlias()!=null)
			clickable = fieldFactory.createClickable(
					getAlias(),
					elContext.evaluate(getXpath()),
					elContext.evaluate(getToPageAlias())
			);
		else
			clickable = fieldFactory.createClickable(
				getAlias(),
				elContext.evaluate(getXpath())
			);
		return clickable;
	}

	public ElContext getElContext() {
		return elContext;
	}

	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}

	@Override
	public String getToPageAlias() {
		return clickableParams.getToPageAlias();
	}

	public String getXpath() {
		return clickableParams.getXpath();
	}

	public String getAlias() {
		return clickableParams.getAlias();
	}

	@Override
	public void doubleClick() {
		generateClickable().doubleClick();
	}

}
