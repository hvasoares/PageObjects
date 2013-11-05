package com.github.pageobject.impl.field.mutability;

import com.github.pageobject.MutabilityCustomFieldFactory;
import com.github.pageobject.impl.browser.Browser;
import com.github.pageobject.impl.field.CustomField;
import com.github.pageobject.impl.field.TextField;

public class MutabilityFields {

	public static MutabilityCustomFieldFactory getTextFieldFactory(){
		return new MutabilityCustomFieldFactory() {
			
			private Browser browser;

			@Override
			public void setBrowser(Browser value) {
				this.browser = value;
			}
			
			@Override
			public CustomField create(final String alias, final String xpath) {
				return new CustomField() {
					private TextField textField;

					@Override
					public String getAlias() {
						return alias;
					}
					
					@Override
					public void fill(String string) {
						textField.fill(string);
					}
					
					@Override
					public void setBrowser(Browser value) {
						textField = new TextField(alias, xpath, value);
					}
				};
			}
		};
	}
}
