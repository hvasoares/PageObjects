package com.github.hvasoares.pageobjects.impl.mutability;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.NoSuchElementException;

import com.github.hvasoares.pageobjects.MutabilityCustomFieldFactory;
import com.github.hvasoares.pageobjects.impl.FieldFactory;
public class FieldContainer implements FieldContainerI{

	private HashMap<String,List<String>> xpathDb;
	private FieldFactory innerFieldFactory;
	private HashMap<String,MutabilityCustomFieldFactory> factoryDb;

	public FieldContainer(FieldFactory inner) {
		this.innerFieldFactory = inner;
		this.xpathDb = new HashMap<>();
		this.factoryDb = new HashMap<>();
	}

	public void add(String alias, List<String> xpath,
			MutabilityCustomFieldFactory customFieldFactory) {
		this.xpathDb.put(alias,xpath);
		this.factoryDb.put(alias,customFieldFactory);
	}

	@Override
	public void fill(String alias, String value, String[] args) {
		checkArgument(this.xpathDb.containsKey(alias),"The alias '"
			+ alias
			+ "' doesn't exists!!"
		);
		RuntimeException currentException = new RuntimeException("Coudn't fill '"+alias+"'");
		for(String xpath : this.xpathDb.get(alias)){
			try{
				innerFieldFactory.createCustomField(
						this.factoryDb.get(alias)
						.create(alias, getTransformedXpath(xpath, args))
				).fill(value);
				return;
			}catch(NoSuchElementException ex){
				currentException.initCause(ex);
				continue;
			}
		}
		throw currentException;
	}
	
	private String getTransformedXpath(String xpath,String[] args){
		FluidXpath result = new FluidXpath(xpath);
		String[] newArgs = args;
		if(args.length==1)
			newArgs = new String[]{"value",args[0]};
		
		for(Entry<String, String> e : Utils.toMapSetEntry(args)){
			result.bind(e.getKey(), e.getValue());
		}
		return result.getTransformedXpath();
	}
}
