package com.github.hvasoares.pageobjects.mixin;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.github.hvasoares.pageobjects.PageObject;
import com.github.hvasoares.pageobjects.PageObjectBuilder;
import com.github.hvasoares.pageobjects.PageObjectBuilderFactory;
import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;
import com.github.hvasoares.pageobjects.mixin.MixerI;
import com.github.hvasoares.pageobjects.mixin.MixinI;
import com.github.hvasoares.pageobjects.mixin.MixinPageRepository;


public class MixinPageRepositoryTest {

	private MixinPageRepository instance;
	@Mock private MixinI mixin1;
	@Mock private MixinI mixin2;
	@Rule public JUnitRuleMockery ctx = new JUnitRuleMockery();
	@Mock private MixerI mixer;
	@Mock private PageObjectBuilderFactory factory;
	@Mock private SerialPageObjectBuilderI serialBuilder;
	@Mock private PageObjectBuilder pageBuilder1;
	@Mock private PageObjectBuilder pageBuilder2;

	@Test
	public void shouldIterateOverAllMixinAndWorkOnPageObjectBuilder() {
		instance = new MixinPageRepository(mixin1,mixin2);
		instance.setMixer(mixer);
		final ArrayList<PageObject> serialBuildGetAllResult = new ArrayList<PageObject>();
		ctx.checking(new Expectations(){{
			oneOf(mixer).add(mixin1);
			oneOf(mixer).add(mixin2);
			
			allowing(mixin1).getName();will(returnValue("mixin1"));
			allowing(mixin2).getName();will(returnValue("mixin2"));
			
			oneOf(factory).createSerialPageObjectBuilder();
			will(returnValue(serialBuilder));
			
			oneOf(serialBuilder).newPage("mixin1");
			will(returnValue(pageBuilder1));
			oneOf(mixer).build(pageBuilder1,mixin1);
			
			oneOf(serialBuilder).newPage("mixin2");
			will(returnValue(pageBuilder2));
			oneOf(mixer).build(pageBuilder2,mixin2);
			
			oneOf(serialBuilder).getAll();
			will(returnValue(serialBuildGetAllResult));
		}});
		
		instance.setBuilderFactory(factory);
		assertEquals(instance.getPages(),serialBuildGetAllResult);
		
	}

}
