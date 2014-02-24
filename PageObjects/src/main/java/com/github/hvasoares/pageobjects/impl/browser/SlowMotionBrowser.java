package com.github.hvasoares.pageobjects.impl.browser;

public class SlowMotionBrowser implements Browser {

	private Browser inner;
	
	public SlowMotionBrowser(Browser inner) {
		super();
		this.inner = inner;
	}	
	
	public void click(String xpath) {
		inner.click(xpath);
		sleep();
	}

	private void sleep() {
		String shouldSleep = System.getProperty("PAGEOBJECT_SET_SLOWMOTION");
		if(shouldSleep!=null && !shouldSleep.equals("TRUE"))
			return;
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}



	public void fill(String xpath, String value) {
		inner.fill(xpath, value);
		sleep();
	}



	public void goToStartUrl(String url) {
		inner.goToStartUrl(url);
		sleep();
	}



	public void close() {
		inner.close();
		sleep();
	}
}
