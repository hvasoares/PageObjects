package com.github.hvasoares.pageobjects.mixin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.CaseFormat;

public abstract class Mixin implements MixinI{
	private String name;
	private String[] depedencies;
	
	public Mixin(){
		name = 	CaseFormat.UPPER_CAMEL.
				to(
						CaseFormat.LOWER_CAMEL, 
						this.getClass().getSimpleName()
				);
	}
	
	public Mixin(String name){
		this.name = name;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public final void mixin(String... args) {
		depedencies = args;
	}

	@Override
	public final List<String> getDependencies() {
		if(depedencies!=null)
			return Arrays.asList(depedencies);
		return new ArrayList<String>();
	}

}
