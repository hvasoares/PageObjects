package com.github.hvasoares.pageobjects.dsl;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.mixin.Mixin;

public class MixinDSL extends Mixin{

	private PageObjectBuilder builder;

	@Override
	public void build(PageObjectBuilder builder) {
		this.setBuilder(builder);
		build();
	}
		
	public void build() {
		
	}

	private PageObjectBuilder builder() {
		return builder;
	}

	private void setBuilder(PageObjectBuilder builder) {
		this.builder = builder;
	}

}
