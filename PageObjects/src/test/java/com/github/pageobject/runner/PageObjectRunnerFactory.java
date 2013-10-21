package com.github.pageobject.runner;

import com.github.pageobject.AbstractFactory;

public interface PageObjectRunnerFactory {

	PageObjectRunner create(Class<?> clazz, AbstractFactory defaultFactory);

}
