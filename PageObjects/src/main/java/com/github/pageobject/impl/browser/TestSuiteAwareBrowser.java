package com.github.pageobject.impl.browser;

public class TestSuiteAwareBrowser implements Browser{

	private static TestSuiteAwareBrowser instance;
	public Browser inner;
	private BrowserLocker locker;

	private TestSuiteAwareBrowser(){
	
	}
	
	public static TestSuiteAwareBrowser getInstance(Browser browserImlTest){
		if(instance==null)
			instance = new TestSuiteAwareBrowser();
		if(browserImlTest!=null)
			instance.inner =browserImlTest;
		return instance;
	}
	
	public static TestSuiteAwareBrowser getInstance(){
		return getInstance(null);
	}
	
	public void click(String xpath) {
		inner.click(xpath);
	}

	public void fill(String xpath, String value) {
		inner.fill(xpath, value);
	}

	public void goToStartUrl(String url) {
		inner.goToStartUrl(url);
	}

	public void close() {
		
	}
	
	public void setTestSuiteEnded(Boolean value){

	}
	
	public void setBrowserLocker(BrowserLocker value){
		changeLocker(null, value);
	}
	
	private void changeLocker(BrowserLocker oldOne, BrowserLocker newOne){
		if(locker==oldOne)
			locker = newOne;	
	}
	
	public void close(BrowserLocker locker){
		if(this.locker==locker && inner!=null)
			inner.close();
	}
	
	
}
