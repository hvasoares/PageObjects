package com.github.hvasoares.pageobjects.mixin;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public class PrototypeMixinTest {

	@Mock private MixinI inner;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private PageObjectBuilder builder;

	@Test
	public void shouldExtendTheInnerMixin() {
		PrototypeMixin instance = new PrototypeMixin(inner){
			@Override
			protected void extend(PageObjectBuilder builder){
				builder.addClickable("someAlias", "someXpath");
			}
			
		};
		
		ctx.checking(new Expectations(){{
			oneOf(inner).getName(); will(returnValue("innerName"));
			oneOf(inner).build(builder);
			oneOf(builder).addClickable("someAlias", "someXpath");
		}});
		
		assertEquals("innerName", instance.getName());
		
		instance.build(builder);
		
		
	}

}
