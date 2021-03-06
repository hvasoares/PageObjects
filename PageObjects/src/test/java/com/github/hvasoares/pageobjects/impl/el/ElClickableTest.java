package com.github.hvasoares.pageobjects.impl.el;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.Clickable;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.el.ElClickable;
import com.github.hvasoares.pageobjects.impl.el.ElContext;
import com.github.hvasoares.pageobjects.impl.field.ClickableParams;

public class ElClickableTest {

	private FieldFactory fieldFactory;
	private ElContext elContext;
	private ElClickable instance;
	private Mockery ctx;

	@Test
	public void shouldEvaluateXpathAndToPage() {
		ctx = new Mockery();
		fieldFactory = ctx.mock(FieldFactory.class);
		elContext = ctx.mock(ElContext.class);
		
		ClickableParams params = new ClickableParams(
				"someAlias",
				"someXpath",
				"toPage"
		);
		
		instance = new ElClickable(params, elContext, fieldFactory);
		ctx.checking(new Expectations(){{
			oneOf(elContext).evaluate("someXpath");
			will(returnValue("someXpathEvaluated"));
			oneOf(elContext).evaluate("toPage");
			will(returnValue("toPageEvaluated"));
			
			oneOf(fieldFactory).createClickable(
						"someAlias",
						"someXpathEvaluated",
						"toPageEvaluated"
					);
			Clickable clickable = ctx.mock(Clickable.class);
			will(returnValue(clickable ));
			oneOf(clickable).click();
		}});
		instance.click();
		
		ctx.assertIsSatisfied();
	}

}
