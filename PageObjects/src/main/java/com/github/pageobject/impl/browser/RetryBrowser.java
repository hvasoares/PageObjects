package com.github.pageobject.impl.browser;

public class RetryBrowser implements Browser {
	private Browser inner;
	private int timeOut;
	
	public RetryBrowser(Browser inner) {
		this.inner = inner;
		this.timeOut = 20000;
	}

	@Override
	public synchronized void click(String string) {
		try{
			inner.click(string);
		}catch(Exception e){
			try {
				wait(timeOut);
				inner.click(string);
			} catch (InterruptedException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	@Override
	public synchronized void fill(String xpath, String value) {
		try{
			inner.fill(xpath,value);
		}catch(Exception e){
			try {
				wait(timeOut);
				inner.fill(xpath,value);
			} catch (InterruptedException e1) {
				throw new RuntimeException(e1);
			}
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

	public void setTimeout(int i) {
		this.timeOut =i;
	}

}
