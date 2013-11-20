package com.github.hvasoares.pageobjects.impl;

import com.github.hvasoares.pageobjects.impl.Field;

public interface FieldContainer{

	void fill(String aliase, String value);

	void add(Field field);

}
