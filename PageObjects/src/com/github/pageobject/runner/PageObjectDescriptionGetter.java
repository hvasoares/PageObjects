package com.github.pageobject.runner;

import com.github.jsteak.DescriptionGetter;

public interface PageObjectDescriptionGetter extends DescriptionGetter{

	public abstract PageObjectRepository getRepository();
	
}
