package com.github.pageobject;

import java.util.List;

import com.github.pageobject.PageObject;
import com.github.pageobject.PageObjectBuilder;

public interface SerialPageObjectBuilder extends PageObjectBuilder {

	public abstract SerialPageObjectBuilder newPage(String name);

	public abstract List<PageObject> getAll();

}
