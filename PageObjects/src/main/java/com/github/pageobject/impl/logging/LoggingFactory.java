package com.github.pageobject.impl.logging;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.github.pageobject.PageObject;
import com.github.pageobject.impl.ProxyStatePageObjectAdapter;
import com.github.pageobject.impl.browser.Browser;

public abstract class LoggingFactory {
	private static Logger log;

	public static ProxyStatePageObjectAdapter createStatePageObjectLogging(){
		return new StatePageObjectLog(getLog());
	}
	
	public static Browser createBrowserLogging(Browser inner){
		return new BrowserLog(inner, getLog());
	}

	private static Logger getLog() {
		if(log!=null)
			return log;
		log = Logger.getLogger(PageObject.class);
		BasicConfigurator.configure();
		return log;
	}
}
