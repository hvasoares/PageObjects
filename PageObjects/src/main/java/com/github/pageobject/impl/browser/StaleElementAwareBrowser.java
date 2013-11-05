package com.github.pageobject.impl.browser;

import org.openqa.selenium.StaleElementReferenceException;

public class StaleElementAwareBrowser implements Browser{

	private Browser inner;

	public StaleElementAwareBrowser(Browser inner) {
		this.inner = inner;
	}

	@Override
	public void click(String xpath) {
		try{
			inner.click(xpath);
		}catch(StaleElementReferenceException ex){
			inner.click(xpath);
		}
	}

	@Override
	public void fill(String xpath, String value) {
		try{
			inner.fill(xpath, value);
		}catch(StaleElementReferenceException ex){
			inner.fill(xpath, value);
		}
	}

	@Override
	public void goToStartUrl(String url) {
		inner.goToStartUrl(url);
	}

	@Override
	public void close() {
		inner.close();
	}
	
	

}
