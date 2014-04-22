package com.github.hvasoares.pageobjects;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

public interface RepositoryAwareFactory extends AbstractFactory {
	void setRepository(PageObjectRepository value);

	public abstract PageObjectRepository getRepository();

	void setWebDriver(WebDriver value);
}
