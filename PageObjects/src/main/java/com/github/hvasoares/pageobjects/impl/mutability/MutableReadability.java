package com.github.hvasoares.pageobjects.impl.mutability;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.NoSuchElementException;

import com.github.hvasoares.pageobjects.impl.Readability;
import com.github.hvasoares.pageobjects.impl.readability.ReadabilityFactory;
public class MutableReadability implements MutableReadabilityI{
	
	private FluidXpathFactoryI xpathFactory;
	private HashMap<String,FluidXpathI> db;
	private ReadabilityFactory readabilityFactory;

	public MutableReadability(ReadabilityFactory readabilityFactory, FluidXpathFactoryI xpathFactory) {
		this.xpathFactory =xpathFactory;
		this.readabilityFactory = readabilityFactory;
		this.db = new HashMap<String, FluidXpathI>();
	}

	@Override
	public void addReadProperty(String alias, String xpath) {
		this.db.put(alias,xpathFactory.create(dealWithPositioning(xpath)));
	}

	private String dealWithPositioning(String xpath) {
		if(xpath.indexOf(":positioningMarker")!=-1)
			return xpath.replace(":positioningMarker", "position()=:positioningMarker");
		return xpath;
	}

	@Override
	public String read(String... args) {
		checkArgument(args.length>=2,"The format of argument must be: alias , [singleValue] | [placeHolderName, value]+ ");
		checkNotNull(db.get(args[0]),"The readability aliased with '"+args[0]+ "' not exists.");
		String[] newArgs = args;
		if(args.length==2){
			newArgs = new String[]{args[0],"value",args[1]};
		}
		FluidXpathI xpath = db.get(args[0]);
		for(Entry<String, String> e : Utils.toMapSetEntry(Arrays.copyOfRange(newArgs, 1, newArgs.length))){
			xpath.bind(e.getKey(), e.getValue());
		}
		String result = xpath.getTransformedXpath();
		checkState(
				result.indexOf(":positioningMarker")==-1,
				"This xpath has a :positioningMarker you must use readAsList")
		;
		Readability read = readabilityFactory.create();
		read.setProperty(newArgs[0], result);
		return read.read(newArgs[0]);
	}
	
	@Override
	public List<String> readAsList(String ...args){
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i=1; true; i++)
			try{
				result.add(read(Utils.copyAndConcatenate(args,"positioningMarker",Integer.toString(i))));
			}catch(NoSuchElementException ex){
				return result;
			}
	}

}
