package com.github.pageobject;

import com.github.pageobject.impl.SinglePageObjectBuilder;

public interface PageObjectBuilderFactory {

	public abstract SinglePageObjectBuilder createSinglePageObjectBuilder();
	public SerialPageObjectBuilder createSerialPageObjectBuilder();

}
