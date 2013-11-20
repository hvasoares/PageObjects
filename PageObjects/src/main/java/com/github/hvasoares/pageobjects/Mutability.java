package com.github.hvasoares.pageobjects;

import java.util.List;

public interface Mutability {

	PageObjectBuilder addClickable(String alias, String xpath, String pageToTransitioning);
	PageObjectBuilder addClickable(String alias, String xpath);
	PageObjectBuilder extendsClickable(String ...args);
	StatePageObject click(String ... args);
	PageObjectBuilder addReadProperty(String alias, String xpath);
	List<String> readAsList(String... args);
	StatePageObject doubleClick(String ...args);
	String read(String ... args);
	PageObjectBuilder addCustomField(String alias, String xpath, MutabilityCustomFieldFactory mutabilityCustomFieldFactory);
	PageObjectBuilder addCustomField(String alias, List<String> xpath, MutabilityCustomFieldFactory mutabilityCustomFieldFactory);
	StatePageObject fill(String alias, String value, String ... args);
	MutableAssertiveness assertiveness();
}
