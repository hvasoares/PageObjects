package com.github.pageobject.impl.mutability;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.pageobject.StatePageObject;

public class ExecutionTimeTest {

	@Mock private MutabilityContextI mCtx;
	@Mock private StatePageObject state;
	@Rule public JUnitRuleMockery ctx =new JUnitRuleMockery();

	@Test
	public void shouldClick() {
		ExecutionTime instance = new ExecutionTime(mCtx);
		ctx .checking(new Expectations(){{
			oneOf(mCtx).click("someAlias",Arrays.asList("placeH1","value1").toArray(new String[2]));
		}});
		
		instance.click("someAlias","placeH1","value1");
	}

}
