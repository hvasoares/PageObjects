package com.github.pageobject;

import com.github.pageobject.impl.SerialPageObjectBuilder;

public interface PageObjectBuilderFactory {

	public abstract PageObjectBuilder createPageObjectBuilder();
	public SerialPageObjectBuilder createSerialPageObjectBuilder();

}
