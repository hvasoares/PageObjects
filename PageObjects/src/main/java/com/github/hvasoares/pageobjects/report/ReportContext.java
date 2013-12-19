package com.github.hvasoares.pageobjects.report;

import java.util.Date;
import java.util.List;

import org.junit.runner.Description;


public class ReportContext implements ReportContextI {
	 
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
	
	@Override
	public void tryReportEvent(String event){
		if(shouldNotTakeScreenShotOf(event))
			return;
			
		for ( ReportStrategy strategy : strategies ){
			strategy.report(  this, event );
		}
		nextStep();
	}
	
	private boolean shouldNotTakeScreenShotOf(String event){
		return !(settings.isEnabled() && settings.getEnabledEvents().contains(event));
	}

	 
	@Override
	public ReportSettings getSettings(){
		return settings;
	}
	
	@Override
	public int getCurrentStep() {
		return currentStep;
	}
	
	private void nextStep(){
		currentStep += 1;
	}
	
	@Override
	public Description getCurrentTest() {
		return currentTest;
	}
	
	@Override
	public void setCurrentTest(Description currentTest) {
		this.currentTest = currentTest;
		currentStep = 0;
	}
	 
	@Override
	public Date getExecutionDate() {
		return executionDate;
	}	
}
