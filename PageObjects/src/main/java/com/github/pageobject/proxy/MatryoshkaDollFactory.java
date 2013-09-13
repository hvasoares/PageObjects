package com.github.pageobject.proxy;



@SuppressWarnings("unchecked")
public class MatryoshkaDollFactory <T,S extends DecoratorObject<T>  >{
	public T create(T realObject,S ...proxies){
		int i=0;
		for(i = 0; i<proxies.length-1; i++){
			proxies[i].setInner(proxies[i+1].self());
		}
		
		proxies[i].setInner(realObject);
		return proxies[0].self();
	}
}
