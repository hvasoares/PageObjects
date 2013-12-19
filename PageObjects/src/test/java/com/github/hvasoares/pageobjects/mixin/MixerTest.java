package com.github.hvasoares.pageobjects.mixin;

import java.util.ArrayList;
import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.mixin.Mixer;
import com.github.hvasoares.pageobjects.mixin.MixinI;

public class MixerTest {

	private Mixer instance;
	@Mock private MixinI mixin1;
	@Mock private MixinI mixin5;
	@Mock private MixinI mixin4;
	@Mock private MixinI mixin3;
	@Mock private MixinI mixin2;
	@Mock private PageObjectBuilder pageObject;
	
	@Rule public JUnitRuleMockery ctx= new JUnitRuleMockery();

	@Test
	public void shouldMixPage() {
		instance = new Mixer();
		
		ctx.checking(new Expectations(){{
			allowing(mixin1).getName();will(returnValue("mixin1"));
			allowing(mixin2).getName();will(returnValue("mixin2"));
			allowing(mixin3).getName();will(returnValue("mixin3"));
			allowing(mixin4).getName();will(returnValue("mixin4"));
			allowing(mixin5).getName();will(returnValue("mixin5"));
			
			allowing(mixin1).getDependencies();
			will(returnValue(Arrays.asList("mixin2","mixin3")));
			
			allowing(mixin2).getDependencies();
			will(returnValue(Arrays.asList("mixin3")));
			
			allowing(mixin3).getDependencies();
			will(returnValue(Arrays.asList("mixin4","mixin5")));
			
			allowing(mixin4).getDependencies();
			will(returnValue(new ArrayList<String>()));
			
			allowing(mixin5).getDependencies();
			will(returnValue(Arrays.asList("mixin4")));
			
			Sequence seq = ctx.sequence("invocation sequence");

			oneOf(mixin4).build(pageObject); inSequence(seq);
			oneOf(mixin5).build(pageObject); inSequence(seq);
			oneOf(mixin3).build(pageObject); inSequence(seq);
			oneOf(mixin2).build(pageObject); inSequence(seq);
			oneOf(mixin1).build(pageObject); inSequence(seq);
		}});
		instance.add(mixin1);
		instance.add(mixin2);
		instance.add(mixin3);
		instance.add(mixin4);
		instance.add(mixin5);

		instance.build(pageObject,mixin1);
	}

}
