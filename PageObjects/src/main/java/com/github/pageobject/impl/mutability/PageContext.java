package com.github.pageobject.impl.mutability;

import java.util.HashMap;

import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.FieldFactory;
public class PageContext implements PageContextI{
	private HashMap<String, Mutability> db;
	private FieldFactory fieldFactory;

	public PageContext(FieldFactory fieldFactory){
		this.db = new HashMap<String,Mutability>();
		this.fieldFactory = fieldFactory;
	}
	
	@Override
	public Mutability createMutability(String contextName,PageObjectBuilder pageBuilder) {
		MutabilityContext ctx = new MutabilityContext(fieldFactory);
		BuildTime buildTime = new BuildTime(ctx,new FluidXpathFactory());
		buildTime.setCurrentBuilder(pageBuilder);
		db.put(
			contextName, 
			new Mutability(
				buildTime, 
				new ExecutionTime(ctx)
			)
		);
		return db.get(contextName);
	}

	@Override
	public com.github.pageobject.Mutability get(String pagename,StatePageObject state) {
		Mutability result;
		if(db.containsKey(pagename)){
			result = db.get(pagename);
			result.getExecutionTime().setStateObject(state);
			return result;
		}
		return new NullMultability(pagename);
	}

}
