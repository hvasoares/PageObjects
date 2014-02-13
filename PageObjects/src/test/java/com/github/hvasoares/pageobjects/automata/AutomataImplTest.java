package com.github.hvasoares.pageobjects.automata;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public class AutomataImplTest {

	private AutomataImpl instance;
	@Mock private PageObjectBuilder builder;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();

	@Test
	public void shouldCreateSimpleClickables() {
		instance = new AutomataImpl();
		instance.setBuilder(builder);
		ctx.checking(new Expectations(){{
			oneOf(builder).addClickable(
					"someAlias",
					"//a[normalize-space(.)='someAlias']"
					+ "|//input[ (@type='submit' or @type='button') "
						+ "and ( normalize-space(@value)='someAlias' or normalize-space(@name)='someAlias' or normalize-space(@id)='someAlias') ]"
					+ "|//button[normalize-space(.)='someAlias']"
					+ "|//img[ normalize-space(@title)='someAlias' or normalize-space(@alt)='someAlias']"
			);
		}});
		instance.addClickable("someAlias");
	}
	
	@Test
	public void shouldCreatePageChangerClickables() {
		instance = new AutomataImpl();
		instance.setBuilder(builder);
		ctx.checking(new Expectations(){{
			oneOf(builder).addClickable(
					"someAlias",
					"//a[normalize-space(.)='someAlias']"
					+ "|//input[ (@type='submit' or @type='button') "
						+ "and ( normalize-space(@value)='someAlias' or normalize-space(@name)='someAlias' or normalize-space(@id)='someAlias') ]"
					+ "|//button[normalize-space(.)='someAlias']"
					+ "|//img[ normalize-space(@title)='someAlias' or normalize-space(@alt)='someAlias']",
					"toPage"
			);
		}});
		instance.addClickable("someAlias","toPage");
	}

}
