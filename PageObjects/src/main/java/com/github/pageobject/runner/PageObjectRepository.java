package com.github.pageobject.runner;

import java.util.List;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilderFactory;

public interface PageObjectRepository {

	List<PageObject> getPages();

	void setBuilderFactory(PageObjectBuilderFactory factory);

}
