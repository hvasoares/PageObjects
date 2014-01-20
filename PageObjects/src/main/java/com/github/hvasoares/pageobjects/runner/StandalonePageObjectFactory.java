package com.github.hvasoares.pageobjects.runner;

import com.github.hvasoares.pageobjects.DefaultFactory;

public class StandalonePageObjectFactory<T> {
	public T create(Class<T> clazz){
		ObjectConstructor objConst = new ObjectConstructor();
		DefaultFactory factory = new DefaultFactory();
		
		StandalonePageObject<T> stdPageObj = new StandalonePageObject<T>(
				objConst.construct(clazz), 
				factory,
				objConst
		);
		
		return stdPageObj.getObject();
	}
}
