package com.github.pageobject;

import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.CustomField;

public interface MutabilityCustomFieldFactory {
	CustomField create(String alias, String xpath);
	void setBrowser(Browser value);
}
