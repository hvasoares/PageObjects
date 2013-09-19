package com.github.pageobject;

public interface Mutability {

	PageObjectBuilder addClickable(String alias, String xpath, String pageToTransitioning);
	PageObjectBuilder addClickable(String alias, String xpath);
	PageObjectBuilder extendsClickable(String ...args);
	StatePageObject click(String ... args);

}
