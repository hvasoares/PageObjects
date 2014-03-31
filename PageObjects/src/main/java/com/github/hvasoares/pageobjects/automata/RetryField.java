package com.github.hvasoares.pageobjects.automata;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.language.bm.Languages.SomeLanguages;
import org.aspectj.lang.reflect.FieldSignature;
import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.impl.browser.Browser;
import com.github.hvasoares.pageobjects.impl.field.WebDriverAwareCustomField;

public class RetryField implements WebDriverAwareCustomField {

	private String xpath;
	private String alias;
	private List<TryField> fields;
	private WebDriver driver;

	public RetryField(String alias, String xpath, List<TryField> fields) {
		this.alias = alias;
		this.xpath = xpath;
		this.fields = fields;
	}

	@Override
	public void setBrowser(Browser value) {
		for(TryField f : fields )
			f.setBrowser(value);
	}

	@Override
	public void fill(String value) {
		this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		for(TryField f : fields)
			if(f.filled(xpath,value)){
				this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				return;
		}
		
		throw new RuntimeException(String.format(
				"Cannot fill field '%s' with value '%s' using the xpath '%s' ", 
				alias,
				value,
				xpath
			));
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public void setWebDriver(WebDriver value) {
		for(TryField f : fields )
			f.setWebDriver(value);
		this.driver = value;
	}

}
