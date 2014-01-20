package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.runner.PageObjectRepository;

public interface RepositoryAwareFactory extends AbstractFactory {
	void setRepository(PageObjectRepository value);

	public abstract PageObjectRepository getRepository();
}
