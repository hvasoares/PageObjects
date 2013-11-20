package com.github.hvasoares.pageobjects.impl.field;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Keys;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.field.EraseBeforeFillTextField;
import com.github.hvasoares.pageobjects.impl.field.TextField;

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
			
			exactly(1).of(machine).readability();will(returnValue(readability));
			oneOf(readability).read("fieldName");
			will(returnValue("0123456"));
			
			Sequence sequence = ctx.sequence("fill sequence");
			oneOf(textField).fill(Keys.chord(repeat(7,Keys.BACK_SPACE)));
			inSequence(sequence );
			
			oneOf(textField).fill("value");
			inSequence(sequence );
		}});
		
		instance.fill("value");
	}

	private Keys[] repeat(int times, Keys backSpace) {
		Keys[] result = new Keys[times];
		for(int i=0; i<times;i++)
			result[i]=backSpace;
		return result;
	}
}
