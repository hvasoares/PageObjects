package com.github.hvasoares.pageobjects.impl.el;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.hvasoares.pageobjects.impl.Field;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
import com.github.hvasoares.pageobjects.impl.el.ElContext;
import com.github.hvasoares.pageobjects.impl.el.ElFieldImpl;

public class ElFieldImplTest {

	private ElContext elContext;
	private FieldFactory fieldFactory;
	private ElFieldImpl instance;
	private Mockery ctx;

	@Test
	public void test() {
		ctx = new Mockery();
		elContext = ctx.mock(ElContext.class);
		fieldFactory = ctx.mock(FieldFactory.class);
		
		instance = new ElFieldImpl(
				elContext,
				fieldFactory,
				"someAlias",
				"someXpath"
		);
		
		ctx.checking(new Expectations(){{
			oneOf(elContext).evaluate("someValue");
			will(returnValue("someValueEvaluated"));
			
			oneOf(elContext).assign("someAlias","someValueEvaluated");
			
			oneOf(elContext).evaluate("someXpath");
			will(returnValue("someXpathEvaluated"));
			
			oneOf(fieldFactory).createTextField("someAlias","someXpathEvaluated");
			Field field = ctx.mock(Field.class);
			will(returnValue(field));
			
			oneOf(field).fill("someValueEvaluated");
		}});
		
		instance.fill("someValue");
		ctx.assertIsSatisfied();
	}

}
