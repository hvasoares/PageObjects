package com.github.hvasoares.pageobjects.runner;

import com.github.jsteak.DescriptionGetter;

public interface PageObjectDescriptionGetter extends DescriptionGetter{

	public abstract PageObjectRepository getRepository();
	
}
