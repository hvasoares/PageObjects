package com.github.hvasoares.pageobjects.aspects;

import static com.google.common.base.Preconditions.checkNotNull;

import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
public aspect PageObjectRepositoryEnhancement {
	public PageObjectBuilderFactory PageObjectRepositoryEnhanced.factory;
	public void PageObjectRepositoryEnhanced.setBuilderFactory(PageObjectBuilderFactory value){
		this.factory = value;
	}
	
	public SerialPageObjectBuilderI PageObjectRepositoryEnhanced.getPageObjectBuilder(){
		checkNotNull(this.factory);
		return this.factory.createSerialPageObjectBuilder();
	}
}
