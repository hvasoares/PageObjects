package com.github.hvasoares.pageobjects.report;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.github.hvasoares.pageobjects.aspects.WebDriverAware;

public class ScreenshotReportStrategy implements ReportStrategy, WebDriverAware {
 
	private PathGenerator pathGenerator = new PathGenerator();
	
	@Override
	public void report( ReportContext reportContext ,  String event ) {	 
		takeScreenshot( reportContext  , event );
	}
	
	private void takeScreenshot( ReportContext reportContext  , String event   ) {		
		try{			
			byte[] screenShot = ( (TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
			
			InputStream in = new ByteArrayInputStream( screenShot );							 
			OutputStream out = new FileOutputStream( pathGenerator.generate(reportContext, event) );	
			//ImageIO.write( image , "jpg", out );
			IOUtils.copy(in, out);
		} catch ( IOException  e ){			
			e.printStackTrace();
		}
	} 	 
}
