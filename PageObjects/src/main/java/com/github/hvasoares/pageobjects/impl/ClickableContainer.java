package com.github.hvasoares.pageobjects.impl;

public interface ClickableContainer {

	void click(String buttonAlias);

	void add(Clickable click);

	void doubleClick(String buttonAlias);

}
