package com.github.hvasoares.pageobjects.report;

import java.util.Date;
import java.util.List;

import org.junit.runner.Description;

public interface ReportContextI {

	public abstract ReportSettings getSettings();

	public abstract List<ReportStrategy> getStrategies();

	public abstract int getCurrentStep();

	public abstract void nextStep();

	public abstract Description getCurrentTest();

	public abstract void setCurrentTest(Description currentTest);

	public abstract Date getExecutionDate();

	public abstract boolean shouldTakeScreenShotOf(String event);

	public abstract void tryReportEvent(String event);

}