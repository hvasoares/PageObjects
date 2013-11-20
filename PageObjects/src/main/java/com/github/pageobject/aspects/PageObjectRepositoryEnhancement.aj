package com.github.pageobject.aspects;

import static com.google.common.base.Preconditions.checkNotNull;

import com.github.pageobject.PageObjectBuilderFactory;
import com.github.pageobject.SerialPageObjectBuilderI;
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
