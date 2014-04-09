package com.github.hvasoares.pageobjects.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.hvasoares.pageobjects.impl.webdriver.FirefoxWebDriverFactory;
import com.github.hvasoares.pageobjects.impl.webdriver.WebDriverFactory;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PageObjectTest {
	Class<? extends PageObjectRepository> repository();

	Class<? extends WebDriverFactory> driverFactory() default FirefoxWebDriverFactory.class;
}
