package com.github.pageobject.impl.mutability;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import com.github.pageobject.MutabilityCustomFieldFactory;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.field.CustomField;

public class FieldContainerTest {

	private FieldContainer instance;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private MutabilityCustomFieldFactory customFieldFactory;
	@Mock private FieldFactory inner;

	@Test
	public void shouldFillACustomField() {
		instance = new FieldContainer(inner);
		
		instance.add("alias",Arrays.asList("xpath1:withPlaceHolder","xpath2:withPlaceHolder"),customFieldFactory);
		
		ctx.checking(new Expectations(){{
			oneOf(customFieldFactory).create("alias", "xpath1Modified");
			CustomField field = ctx.mock(CustomField.class,"customFieldWithError");
			will(returnValue(field));
			
			oneOf(inner).createCustomField(field);
			will(returnValue(field));
			
			oneOf(field).fill("someValue");
			will(throwException(new NoSuchElementException("some Reason")));
			
			oneOf(customFieldFactory).create("alias", "xpath2Modified");
			CustomField field2 = ctx.mock(CustomField.class,"customFieldWithoutError");
			will(returnValue(field2));
			
			oneOf(inner).createCustomField(field2);
			will(returnValue(field2));
			
			oneOf(field2).fill("someValue");
		}});
		
		instance.fill("alias","someValue",array("withPlaceHolder","Modified"));
	}
	
	private <T> T[] array(T ... values){
		return values;
	}

}
