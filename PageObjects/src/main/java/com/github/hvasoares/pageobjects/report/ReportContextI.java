package com.github.hvasoares.pageobjects.report;

import java.util.Date;
import java.util.List;

import org.junit.runner.Description;

public interface ReportContextI {

	public abstract ReportSettings getSettings();

	public abstract int getCurrentStep();

	public abstract Description getCurrentTest();

	public abstract void setCurrentTest(Description currentTest);

	public abstract Date getExecutionDate();

	public abstract void tryReportEvent(String event);

}