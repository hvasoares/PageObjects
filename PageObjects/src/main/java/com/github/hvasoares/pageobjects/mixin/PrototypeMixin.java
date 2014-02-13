package com.github.hvasoares.pageobjects.mixin;

import java.util.List;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public abstract class PrototypeMixin implements MixinI {

	private MixinI inner;

	public PrototypeMixin(MixinI inner) {
		this.inner = inner;
	}


	@Override
	public String getName() {
		return inner.getName();
	}

	@Override
	public void mixin(String... args) {

	}

	@Override
	public void build(PageObjectBuilder builder) {
		inner.build(builder);
		extend(builder);
	}

	@Override
	public List<String> getDependencies() {
		return inner.getDependencies();
	}

	protected abstract void extend(PageObjectBuilder builder);

}
