package com.github.hvasoares.pageobjects.runner;

import java.util.List;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;

public interface PageObjectRepository {

	List<PageObject> getPages();

	void setBuilderFactory(PageObjectBuilderFactory factory);

}
