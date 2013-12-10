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
	 
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#getSettings()
	 */
	@Override
	public ReportSettings getSettings(){
		return settings;
	}
	
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#getStrategies()
	 */
	@Override
	public List<ReportStrategy> getStrategies() {
		return strategies;
	}
	
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#getCurrentStep()
	 */
	@Override
	public int getCurrentStep() {
		return currentStep;
	}
	
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#nextStep()
	 */
	@Override
	public void nextStep(){
		currentStep += 1;
	}
	
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#getCurrentTest()
	 */
	@Override
	public Description getCurrentTest() {
		return currentTest;
	}
	
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#setCurrentTest(org.junit.runner.Description)
	 */
	@Override
	public void setCurrentTest(Description currentTest) {
		this.currentTest = currentTest;
		currentStep = 0;
	}
	 
	/* (non-Javadoc)
	 * @see com.github.hvasoares.pageobjects.report.ReportContextI#getExecutionDate()
	 */
	@Override
	public Date getExecutionDate() {
		return executionDate;
	}
	@Override
	public boolean shouldTakeScreenShotOf(String event){
		return getSettings().isEnabled() && getSettings().getEnabledEvents().contains(event);
	}
	
	@Override
	public void tryReportEvent(String event){
		if(!shouldTakeScreenShotOf(event))
			return;
			
		for ( ReportStrategy strategy : getStrategies() ){
			strategy.report(  this, event );
		}
		nextStep();
	}
}
