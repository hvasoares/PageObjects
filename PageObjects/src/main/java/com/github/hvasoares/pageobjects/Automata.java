package com.github.hvasoares.pageobjects;

public interface Automata extends AutomataFieldFiller {

	PageObjectBuilder addClickable(String alias, String toPage);
	PageObjectBuilder addClickable(String alias);

}
