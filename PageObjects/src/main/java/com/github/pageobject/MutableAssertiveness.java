package com.github.pageobject;

public interface MutableAssertiveness {

	PageObjectBuilder add(String alias, String xpath);

	StatePageObject check(String alias, String ... params);

}
