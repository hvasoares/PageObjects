package com.github.hvasoares.pageobjects.automata;

import com.github.hvasoares.pageobjects.Automata;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import static com.google.common.base.Preconditions.*;

public class AutomataImpl implements Automata{

	
	private PageObjectBuilder builder;
	
	@Override
	public PageObjectBuilder addClickable(String alias, String toPage) {
		return builder.addClickable(alias,returnXpath(alias),toPage);
	}

	@Override
	public PageObjectBuilder addClickable(String alias) {
		return builder.addClickable(alias, returnXpath(alias));
	}
	
	private String returnXpath(String alias){
		return String.format(
				"//a[normalize-space(.)='%1$s']"
				+ "|//input[ (@type='submit' or @type='button') "
					+ "and ( normalize-space(@value)='%1$s' or normalize-space(@name)='%s' or normalize-space(@id)='%1$s') ]"
				+ "|//button[normalize-space(.)='%1$s']"
				+ "|//img[ normalize-space(@title)='%1$s' or normalize-space(@alt)='%1$s']",
				alias
			);
	}
	

	public void setBuilder(PageObjectBuilder builder) {
		this.builder = checkNotNull(builder);
	}
}
