package com.github.pageobject.impl.mutability;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.Set;

import com.github.pageobject.PageObjectBuilder;
public class BuildTime implements BuildTimeI{
	public FluidXpathFactoryI clickableFactory;
	public MutabilityContextI ctx;
	public PageObjectBuilder currentBuilder;
	private String newXpath;
	
	public BuildTime(MutabilityContextI ctx, FluidXpathFactoryI fieldFactory) {
		this.ctx = ctx;
		this.clickableFactory = fieldFactory;
	}

	@Override
	public PageObjectBuilder getPageObjectBuilder() {
		return checkNotNull(
				currentBuilder,
				"The builder cannot be null, do you called setPageObjectBuilder?"
			);
	}

	@Override
	public void add(String alias, String xpathWithPlaceHolders, String toPage) {
		ctx.add(
			alias,
			clickableFactory.create(xpathWithPlaceHolders),
			toPage
		);
	}

	@Override
	public void add(String alias, String xpathWithPlaceHolders) {
		ctx.add(alias, clickableFactory.create(xpathWithPlaceHolders));
	}

	@Override
	public void extendsClickable(String... args) {
		checkArgument(args.length >= 6, "This method requires 6 or more options");
		checkArgument(args[0].equals("from"), "The first argument must be 'from' folowed by the 'super' click alias");
		String from = args[1];
		checkArgument(args[2].equals("newName"),"The third argument must be 'newName' folowed by the new alias");
		String alias = args[3];
		int transitioning = args.length-1;
		
		newXpath= new String(ctx.get(from).getXpath());
		for(Entry<String, String> e : Utils.toMapSetEntry(getPlaceHolders(args))){
			newXpath = newXpath.replace(":"+e.getKey(), e.getValue());
		}
		if(hasPageTransitioning(args))
			ctx.add(alias, clickableFactory.create(newXpath),args[transitioning]);
		else
			ctx.add(alias, clickableFactory.create(newXpath));
	}
	
	private boolean hasPageTransitioning(String []args){
		return args.length%2==1;
	}
	
	private String[] getPlaceHolders(String []args){
		int endIndex = args.length;
		if(hasPageTransitioning(args))
			endIndex -=1;
		return Arrays.copyOfRange(args,4,endIndex);
	}

	public void setCurrentBuilder(PageObjectBuilder currentBuilder) {
		this.currentBuilder = currentBuilder;
	}
}
