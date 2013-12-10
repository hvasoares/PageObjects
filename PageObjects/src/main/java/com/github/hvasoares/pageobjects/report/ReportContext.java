package com.github.hvasoares.pageobjects.report;

import java.util.Date;
import java.util.List;

import org.junit.runner.Description;


public class ReportContext {
	 
	private ReportSettings settings;
	private Description currentTest;
	private int currentStep;	
	private List<ReportStrategy> strategies;
	private Date executionDate;
	
	public ReportContext( ReportSettings settings , List<ReportStrategy> strategies ){
		this.settings = settings;
		this.strategies = strategies;
		executionDate = new Date();
	}
	 
	public ReportSettings getSettings(){
		return settings;
	}
	
	public List<ReportStrategy> getStrategies() {
		return strategies;
	}
	
	public int getCurrentStep() {
		return currentStep;
	}
	
	public void nextStep(){
		currentStep += 1;
	}
	
	public Description getCurrentTest() {
		return currentTest;
	}
	
	public void setCurrentTest(Description currentTest) {
		this.currentTest = currentTest;
		currentStep = 0;
	}
	 
	public Date getExecutionDate() {
		return executionDate;
	}
}
