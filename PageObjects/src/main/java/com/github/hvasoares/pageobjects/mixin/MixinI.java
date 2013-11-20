package com.github.hvasoares.pageobjects.mixin;

import java.util.List;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface MixinI {
	public String getName();
	public void mixin(String ...args);
	public void build(PageObjectBuilder builder);
	public List<String> getDependencies();
}
