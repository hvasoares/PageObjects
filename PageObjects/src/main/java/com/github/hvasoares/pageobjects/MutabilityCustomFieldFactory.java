package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.CustomField;

public interface MutabilityCustomFieldFactory {
	CustomField create(String alias, String xpath);
	void setBrowser(Browser value);
}
