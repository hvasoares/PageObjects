package com.github.hvasoares.pageobjects.report;

import java.util.ArrayList;
import java.util.List;

import com.github.hvasoares.pageobjects.utils.JVMOptions;

public final class ReportContextFactory {
	
	public static final String REPORT_PATH = "-Dpageobject.report.path";
	public static final String REPORT_STRATEGIES = "-Dpageobject.report.strategies";
	
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
		ReportSettings reportSettings = new ReportSettings( rootPath, enabledStrategies );
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
