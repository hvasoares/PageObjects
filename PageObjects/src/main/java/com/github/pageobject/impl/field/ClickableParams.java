package com.github.pageobject.impl.field;


public class ClickableParams  {

	private String toPageAlias;
	private String xpath;
	private String alias;

	public ClickableParams(String aliase, String xpath, String toPageAlias) {
		this.alias = aliase;
		this.xpath = xpath;
		this.toPageAlias = toPageAlias;
	}

	

	public ClickableParams(String alias,String xpath) {
		this.xpath = xpath;
		this.alias = alias;
	}



	public String getToPageAlias() {
		return toPageAlias;
	}

	public String getXpath() {
		return xpath;
	}

	public String getAlias() {
		return alias;
	}

	public boolean isStateChange() {
		return toPageAlias!=null;
	}
}
