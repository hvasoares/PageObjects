package com.github.pageobject.impl.field;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Keys;

import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.Readability;

public class EraseBeforeFillTextFieldTest {

	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	@Mock private TextField textField;
	@Mock private StatePageObject machine;
	@Mock private Readability readability;

	@Test
	public void shouldEraseTheTextFieldBeforeFillIt() {
		EraseBeforeFillTextField instance = new EraseBeforeFillTextField(textField,machine);
		ctx.checking(new Expectations(){{
			atLeast(1).of(textField).getAlias();will(returnValue("fieldName"));
			
			exactly(4).of(machine).readability();will(returnValue(readability));
			exactly(4).of(readability).read("fieldName");
			will(onConsecutiveCalls(
					returnValue("aaa"),
					returnValue("aa"),
					returnValue("a"),
					returnValue("")
			));
			
			exactly(3).of(textField).fill(Keys.chord(Keys.BACK_SPACE));
			
			oneOf(textField).fill("value");
		}});
		
		instance.fill("value");
	}

}
