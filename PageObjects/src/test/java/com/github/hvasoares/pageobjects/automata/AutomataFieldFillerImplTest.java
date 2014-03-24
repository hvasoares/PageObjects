package com.github.hvasoares.pageobjects.automata;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.impl.field.CustomField;

public class AutomataFieldFillerImplTest {

	@Mock private RetryFieldFactory retryFieldFactory;
	private AutomataFieldFillerImpl instance;
	@Mock private PageObjectBuilder builder;
	
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();


	@Before
	public void setUp() {
		instance = new AutomataFieldFillerImpl(retryFieldFactory);
		instance.setBuilder(builder);
	}
	
	@Test
	public void shouldCreateAMutability() {
		
		ctx.checking(new Expectations(){{
			oneOf(retryFieldFactory).create("someAlias","//someXpath");
			CustomField customField = ctx.mock(CustomField.class);
			will(returnValue(customField));
			
			oneOf(builder).addCustomField(customField);
		}});
		
		instance.addFieldXpathPair("someAlias", "//someXpath");
	}
	
	@Test
	public void shouldAddSeveralFieldsAtOnce(){
		ctx.checking(new Expectations(){{
			oneOf(retryFieldFactory).create("someAlias1","//someXpath1");
			CustomField customField = ctx.mock(CustomField.class,"customf1");
			will(returnValue(customField));
			
			oneOf(retryFieldFactory).create("someAlias2","//someXpath2");
			CustomField customField1 = ctx.mock(CustomField.class,"customf2");
			will(returnValue(customField1));
			
			oneOf(retryFieldFactory).create("someAlias3","//someXpath3");
			CustomField customField2 = ctx.mock(CustomField.class,"customf3");
			will(returnValue(customField2));
			
			oneOf(builder).addCustomField(customField);
			oneOf(builder).addCustomField(customField1);
			oneOf(builder).addCustomField(customField2);
		}});
		
		instance.addFieldXpathPairs(
				"someAlias1","//someXpath1",
				"someAlias2","//someXpath2",
				"someAlias3","//someXpath3"
			);
	}

}
