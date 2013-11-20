package com.github.hvasoares.pageobjects.mixin;

import com.github.hvasoares.pageobjects.PageObjectBuilder;

public interface MixerI {

	public abstract void add(MixinI mixin);

	public abstract PageObjectBuilder build(PageObjectBuilder builder, MixinI mixin);

}
