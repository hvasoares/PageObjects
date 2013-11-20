package com.github.hvasoares.pageobjects;

import com.github.hvasoares.pageobjects.SerialPageObjectBuilderI;

public interface PageObjectBuilderFactory {

	public abstract PageObjectBuilder createPageObjectBuilder();
	public SerialPageObjectBuilderI createSerialPageObjectBuilder();

}
