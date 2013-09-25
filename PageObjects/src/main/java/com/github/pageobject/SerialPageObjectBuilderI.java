package com.github.pageobject;

import java.util.List;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;

public interface SerialPageObjectBuilderI {

	public abstract PageObjectBuilder newPage(String name);

	public abstract List<PageObject> getAll();

}
