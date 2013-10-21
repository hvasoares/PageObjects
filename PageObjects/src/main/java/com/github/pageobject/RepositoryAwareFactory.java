package com.github.pageobject;

import com.github.pageobject.runner.PageObjectRepository;

public interface RepositoryAwareFactory extends AbstractFactory {
	void setRepository(PageObjectRepository value);
}
