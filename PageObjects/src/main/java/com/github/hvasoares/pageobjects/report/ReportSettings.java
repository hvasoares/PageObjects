package com.github.hvasoares.pageobjects.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportSettings {
	 
	private String rootPath;

	private List<String> strategies = new ArrayList<>();
	  
	public ReportSettings( String rootPath,
			List<String> strategies) {
		super(); 
		this.rootPath = rootPath;
		this.strategies = strategies;
		checkInstance();
	}

	private void checkInstance() {
		if (this.rootPath == null){
			this.rootPath = System.getProperty("user.home");
		} 	
		
		if ( this.strategies == null ){
			this.strategies = Collections.emptyList();
		}
	}

	public boolean isEnabled(){
		return rootPath != null && ! strategies.isEmpty() ;
	}
	
	public String getPath() {
		return rootPath;
	}
	 
	public List<String> getStrategies() {
		return strategies;
	}	  
}
