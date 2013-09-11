package com.github.pageobject.proxy;

import com.github.pageobject.IncompletePageObject;

public class RussianDollPageObjectFactory {
	public IncompletePageObject create(IncompletePageObject realObject,ProxyPageObject ...proxies){
		int i;
		for(i = 0; i<proxies.length-1; i++)
			proxies[i].setInnerObject(proxies[i+1]);
		
		proxies[i+1].setInnerObject(realObject);
		return proxies[0];
	}
}
