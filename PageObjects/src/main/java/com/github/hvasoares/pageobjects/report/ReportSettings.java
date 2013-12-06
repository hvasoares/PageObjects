package com.github.hvasoares.pageobjects.report;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

public class ReportSettings {
	 
	private String rootPath;

	private List<String> strategies = new ArrayList<>();
	  
	public ReportSettings( String rootPath,
			List<String> strategies) {
		super();
		Preconditions.checkNotNull(rootPath);
		Preconditions.checkNotNull(strategies);
		this.rootPath = rootPath;
		this.strategies = strategies;
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
