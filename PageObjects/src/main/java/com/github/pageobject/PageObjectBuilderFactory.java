package com.github.pageobject;

import com.github.pageobject.SerialPageObjectBuilderI;

public interface PageObjectBuilderFactory {

	public abstract PageObjectBuilder createPageObjectBuilder();
	public SerialPageObjectBuilderI createSerialPageObjectBuilder();

}
