package com.github.pageobject.impl.mutability;

import java.util.Arrays;
import java.util.List;

import com.github.pageobject.Mutability;
import com.github.pageobject.MutabilityCustomFieldFactory;
import com.github.pageobject.MutableAssertiveness;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public class MutabilityImpl implements Mutability{
	private ClickableI clickable;
	private MutableReadabilityI readability;
	private FieldContainerI fields;
	private MutableAssertivenessImpl assertiveness;
	public MutabilityImpl(
		ClickableI clickable, 
		MutableReadabilityI readability, 
		FieldContainer fieldContainer,
		MutableAssertivenessImpl assertiveness
	) {
		super();
		this.clickable = clickable;
		this.readability = readability;
		this.fields=fieldContainer;
		this.assertiveness = assertiveness;
		
		this.assertiveness.setStatePageObject(clickable.getStatePageObject());
		this.assertiveness.setPageObjectBuilder(clickable.getPageObjectBuilder());
	}
	public StatePageObject click(String... args) {
		return clickable.click(args);
	}
	public ExecutionTimeI getExecutionTime() {
		return clickable.getExecutionTime();
	}
	public PageObjectBuilder extendsClickable(String... args) {
		return clickable.extendsClickable(args);
	}
	public PageObjectBuilder addClickable(String alias, String xpath) {
		return clickable.addClickable(alias, xpath);
	}
	public PageObjectBuilder addClickable(String alias, String xpath,
			String pageToTransitioning) {
		return clickable.addClickable(alias, xpath, pageToTransitioning);
	}
	public String read(String... args) {
		return readability.read(args);
	}
	public PageObjectBuilder addReadProperty(String alias, String xpath) {
		readability.addReadProperty(alias, xpath);
		return clickable.getPageObjectBuilder();
	}
	@Override
	public List<String> readAsList(String... args) {
		return readability.readAsList(args);
	}
	@Override
	public StatePageObject doubleClick(String... args) {
		return clickable.doubleClick(args);
	}
	@Override
	public PageObjectBuilder addCustomField(String alias, String xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory) {
		return addCustomField(alias, Arrays.asList(xpath), mutabilityCustomFieldFactory);
	}
	@Override
	public StatePageObject fill(String alias, String value, String... args) {
		fields.fill(alias,value,args);
		return clickable.getStatePageObject();
	}
	@Override
	public PageObjectBuilder addCustomField(String alias, List<String> xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory) {
		fields.add(alias, xpath, mutabilityCustomFieldFactory);
		return clickable.getPageObjectBuilder();
	}
	@Override
	public MutableAssertiveness assertiveness() {
		return assertiveness;
	}
	
}
