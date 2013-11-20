package com.github.hvasoares.pageobjects;

import java.util.List;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface SerialPageObjectBuilderI {

	public abstract PageObjectBuilder newPage(String name);

	public abstract List<PageObject> getAll();

}
