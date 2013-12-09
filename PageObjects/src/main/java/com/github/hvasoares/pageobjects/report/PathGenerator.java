package com.github.hvasoares.pageobjects.report;

import java.io.File;
import java.text.SimpleDateFormat;

class PathGenerator {

	public String generate( ReportContext context, String event ){

		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String formatedDate = formater.format( context.getExecutionDate() );
		
		File dir = new File( context.getSettings().getPath() 
				+ File.separator
				+ formatedDate
				+ File.separator 
				+ getContainerFolder( context ) );
		if ( !dir.exists() ){
			dir.mkdirs();
		}
		return dir.getAbsolutePath() + File.separator + getFileName(context, event);
	}

	private String getContainerFolder(ReportContext context ){
		String name = context.getCurrentTest().getDisplayName();
		int indexOfEndMethodName = name.indexOf("(");
		String className = name.substring( indexOfEndMethodName + 1 , name.length() - 1 );		
		return className;
	} 

	private String getFileName(  ReportContext reportContext , String event ){	
		int currentStep = reportContext.getCurrentStep();		
		String name = reportContext.getCurrentTest().getDisplayName();
		int indexOfEndMethodName = name.indexOf("(");
		String methodName = name.substring(0, indexOfEndMethodName);		 
		return currentStep +  "-" + methodName + "-" + event + ".jpg";
	}
}
