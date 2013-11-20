package com.github.hvasoares.pageobjects.mixin;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
import com.github.hvasoares.pageobjects.runner.PageObjectRepository;
public class MixinPageRepository implements PageObjectRepository {

	private SerialPageObjectBuilderI serialPageObjectBuidler;
	private MixerI mixer;
	private MixinI[] mixins; 

	public MixinPageRepository(MixinI ... mixins){
		checkArgument(mixins.length>0, "You should provide at least 1 Mixin class.");
		this.mixins = mixins;
		mixer = new Mixer();
	}
	
	@Override
	public final List<PageObject> getPages() {
		for(MixinI mixin : mixins)
			mixer.add(mixin);
		
		for(MixinI mixin : mixins){
			mixer.build(
					serialPageObjectBuidler.newPage(mixin.getName()),
					mixin
				);
		}
			
		return serialPageObjectBuidler.getAll();
	}

	@Override
	public final void setBuilderFactory(PageObjectBuilderFactory factory) {
		serialPageObjectBuidler = factory.createSerialPageObjectBuilder();
	}
	
	void setMixer(MixerI value){
		mixer = value;
	}
}
