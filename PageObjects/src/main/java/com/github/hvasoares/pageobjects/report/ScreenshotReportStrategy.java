package com.github.hvasoares.pageobjects.report;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.github.hvasoares.pageobjects.aspects.WebDriverAware;

public class ScreenshotReportStrategy implements ReportStrategy, WebDriverAware {
 
	private PathGenerator pathGenerator = new PathGenerator();
	
	@Override
	public void report( ReportContext reportContext ,  String event ) {	 
		takeScreenshot( reportContext  , event );
	}
	
	private void takeScreenshot( ReportContext reportContext  , String event   ) {		
		try{			
		//	byte[] screenShot = ( (TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			//InputStream in = new ByteArrayInputStream( image.get screenShot);							 
			OutputStream out = new FileOutputStream( pathGenerator.generate(reportContext, event) );	
			ImageIO.write( image , "jpg", out );
			//IOUtils.copy(in, out);
		} catch ( IOException  | AWTException e ){			
			e.printStackTrace();
		}
	} 	 
}
