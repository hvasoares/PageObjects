package com.github.hvasoares.pageobjects.impl.logging;

import org.apache.log4j.Logger;

import com.github.hvasoares.pageobjects.StatePageObject;
import com.github.hvasoares.pageobjects.impl.ProxyStatePageObjectAdapter;

public class StatePageObjectLog extends ProxyStatePageObjectAdapter{
	
	private Logger log;

	public StatePageObjectLog(Logger logger) {
		this.log = logger;
	}

	@Override
	public StatePageObject checkAssertion(String namedAssertion) {
		log.info(String.format("Checking namedAssertion '%s'.",namedAssertion));
		return super.checkAssertion(namedAssertion);
	}

	@Override
	public StatePageObject click(String alias) {
		log.info(String.format("Clicking '%s'.",alias));
		return super.click(alias);
	}

	@Override
	public StatePageObject fill(String field, String value) {
		log.info(String.format("Filling '%s' with '%s'.",field,value));
		try{
			return super.fill(field, value);
		}catch(Exception e){
			log.trace("Error while filling", e);
			throw e;
		}
	}

	@Override
	public void setState(String stateName) {
		log.info(String.format("Entering state '%s'.",stateName));
		super.setState(stateName);
	}

	
}
