package com.github.pageobject.mixin;

import java.util.List;

import com.github.pageobject.PageObjectBuilder;

public interface MixinI {
	public String getName();
	public void mixin(String ...args);
	public void build(PageObjectBuilder builder);
	public List<String> getDependencies();
}
