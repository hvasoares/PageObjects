package com.github.hvasoares.pageobjects.report;

import java.util.ArrayList;
import java.util.List;

import com.github.hvasoares.pageobjects.utils.JVMOptions;

public final class ReportContextFactory {
	
	/**
	 * -Dpageobject.report.path=/home/test/path
	 * or, for windows users
	 * -Dpageobject.report.events=C:\\test\path
	 * 
	 * Default value is user.home value of system properties.
	 */
	public static final String REPORT_PATH = "-Dpageobject.report.path";
	
	/**
	 * -Dpageobject.report.strategies=screenshot
	 * 
	 */
	public static final String REPORT_STRATEGIES = "-Dpageobject.report.strategies";
	
	/**
	 * -Dpageobject.report.events=changePage,click,doubleClick,fill
	 * 
	 * Default value is changePage and it is override if any value is supplied. 
	 */
	public static final String REPORT_EVENTS = "-Dpageobject.report.events";
	
	private static ReportContext context = null;
	
	private static JVMOptions jvmOptions = new JVMOptions();
	
	public static ReportContext createReportContext(){
		if ( context == null ){
			ReportSettings settings = createSettings();	
			List<ReportStrategy> enabledStrategies = createEnabledStrategies( settings );
			context = new ReportContext(settings, enabledStrategies );
		}		
		return context;
	}

	private static ReportSettings createSettings(){
		String rootPath = jvmOptions.getValue( REPORT_PATH );
		List<String> enabledStrategies = jvmOptions.getValues( REPORT_STRATEGIES );
		List<String> enabledEvents = jvmOptions.getValues(REPORT_EVENTS);
		ReportSettings reportSettings = new ReportSettings( rootPath, enabledStrategies , enabledEvents );
		return reportSettings;
	} 
	
	private static List<ReportStrategy> createEnabledStrategies(ReportSettings settings ) {
		List<ReportStrategy> strategies = new ArrayList<>();
		List<String> enabledStrategies = settings.getStrategies();
		if ( enabledStrategies.contains("screenshot") ){
			strategies.add( new ScreenshotReportStrategy() );
		}  
		return strategies;
	}

}
