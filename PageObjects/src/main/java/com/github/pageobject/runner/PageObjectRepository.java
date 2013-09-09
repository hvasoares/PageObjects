package com.github.pageobject.runner;

import java.util.List;

import com.github.pageobject.AssertivePageObject;
import com.github.pageobject.PageObjectBuilderFactory;

public interface PageObjectRepository {

	List<AssertivePageObject> getPages();

	void setBuilderFactory(PageObjectBuilderFactory factory);

}
