package com.github.hvasoares.pageobjects.report;

import java.util.Collections;
import java.util.List;

public class ReportSettings {
	 
	private String rootPath;

	private List<String> strategies;

	private List<String> enabledEvents;
	  
	public ReportSettings( String rootPath,
			List<String> strategies, List<String> enabledEvents) {
		super(); 
		this.rootPath = rootPath;
		this.strategies = strategies;
		this.enabledEvents = enabledEvents;
		checkInstance();
	}

	private void checkInstance() {
		if (rootPath == null){
			rootPath = System.getProperty("user.home");
		} 	
		
		if ( strategies == null ){
			strategies = Collections.emptyList();
		}
		
		if ( enabledEvents == null || enabledEvents.isEmpty() ){
			enabledEvents.add( UserEvents.CHANGE_PAGE );
		}
	}

	public boolean isEnabled(){
		return  !strategies.isEmpty();
	}
	
	public String getPath() {
		return rootPath;
	}
	 
	public List<String> getStrategies() {
		return strategies;
	}	  
	
	public List<String> getEnabledEvents() {
		return enabledEvents;
	}
}
