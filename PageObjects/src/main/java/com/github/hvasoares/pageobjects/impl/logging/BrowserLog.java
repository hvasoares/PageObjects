package com.github.hvasoares.pageobjects.impl.logging;

import org.apache.log4j.Logger;

import com.github.hvasoares.pageobjects.impl.browser.Browser;

public class BrowserLog implements Browser{

	private Browser inner;
	private Logger log;
	public BrowserLog(Browser inner, Logger log){
		this.inner = inner;
		this.log = log;
	}

	public void click(String xpath) {
		log.info("Cliking '"+xpath+"'");
		inner.click(xpath);
	}

	public void fill(String xpath, String value) {
		log.info(String.format("Browser is filling '%s' with '%s'",xpath,value));
		inner.fill(xpath, value);
	}

	public void goToStartUrl(String url) {
		log.info(String.format("Opening url '%s'",url));
		inner.goToStartUrl(url);
	}

	public void close() {
		log.info("Closing the browser.");
		inner.close();
	}
}
