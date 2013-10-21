package com.github.pageobject.impl.mutability;

import java.util.HashMap;

import com.github.pageobject.Mutability;
import com.github.pageobject.PageObjectBuilder;
import com.github.pageobject.StatePageObject;
import com.github.pageobject.impl.FieldFactory;
import com.github.pageobject.impl.readability.ReadabilityImplementationFactory;
public class PageContext implements PageContextI{
	private HashMap<String, MutabilityImpl> db;
	private FieldFactory fieldFactory;

	public PageContext(FieldFactory fieldFactory){
		this.db = new HashMap<String,MutabilityImpl>();
		this.fieldFactory = fieldFactory;
	}
	
	@Override
	public Mutability createMutability(String contextName,PageObjectBuilder pageBuilder) {
		if(this.db.containsKey(contextName))
			return db.get(contextName); 
		MutabilityContext ctx = new MutabilityContext(fieldFactory);
		BuildTime buildTime = new BuildTime(ctx,new FluidXpathFactory());
		buildTime.setCurrentBuilder(pageBuilder);
		db.put(
			contextName, 
			new MutabilityImpl(
					new Clickable(
						buildTime, 
						new ExecutionTime(ctx)
					),
					new MutableReadability(
						ReadabilityImplementationFactory.createDetachedReadabilityFactory(), 
						new FluidXpathFactory()
					),
					new FieldContainer(fieldFactory)
			)
		);
		return db.get(contextName);
	}

	@Override
	public Mutability get(String pagename,StatePageObject state) {
		MutabilityImpl result;
		if(db.containsKey(pagename)){
			result = db.get(pagename);
			result.getExecutionTime().setStateObject(state);
			return result;
		}
		return new NullMultability(pagename);
	}

}
