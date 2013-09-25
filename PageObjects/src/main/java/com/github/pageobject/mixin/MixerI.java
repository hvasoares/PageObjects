package com.github.pageobject.mixin;

import com.github.pageobject.PageObjectBuilder;

public interface MixerI {

	public abstract void add(MixinI mixin);

	public abstract PageObjectBuilder build(PageObjectBuilder builder, MixinI mixin);

}
