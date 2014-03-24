package com.github.hvasoares.pageobjects.automata;

import com.github.hvasoares.pageobjects.impl.field.CustomField;

public interface RetryFieldFactory {

	CustomField create(String alias, String xpath);

}
