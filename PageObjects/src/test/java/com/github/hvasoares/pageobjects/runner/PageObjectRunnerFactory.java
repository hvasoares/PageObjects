package com.github.hvasoares.pageobjects.runner;

import com.github.hvasoares.pageobjects.AbstractFactory;
import com.github.hvasoares.pageobjects.runner.PageObjectRunner;

public interface PageObjectRunnerFactory {

	PageObjectRunner create(Class<?> clazz, AbstractFactory defaultFactory);

}
