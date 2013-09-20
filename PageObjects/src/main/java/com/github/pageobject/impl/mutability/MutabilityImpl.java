package com.github.pageobject.impl.mutability;

import java.util.List;

import com.github.pageobject.Mutability;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;

public class MutabilityImpl implements Mutability{
	private ClickableI clickable;
	private MutableReadabilityI readability;
	public MutabilityImpl(ClickableI clickable, MutableReadabilityI readability) {
		super();
		this.clickable = clickable;
		this.readability = readability;
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
}
