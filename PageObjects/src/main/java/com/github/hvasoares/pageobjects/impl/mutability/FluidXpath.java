package com.github.hvasoares.pageobjects.impl.mutability;

import static com.google.common.base.Preconditions.*;
public class FluidXpath implements FluidXpathI {

	private String xpath;
	private String transformedXpath;

	public FluidXpath(String xpath) {
		this.xpath = xpath;
	}

	@Override
	public String getXpath() {
		return xpath;
	}

	@Override
	public void bind(String placeHolderName, String value) {
		Utils.returnIfMatchesExpression(placeHolderName);
		checkArgument(xpath.indexOf(":"+placeHolderName)>-1,
				"There's no placeholder named '"+placeHolderName+"' in the xpath '"+xpath+"'" 
		);
		if(transformedXpath == null)
			transformedXpath=new String(xpath);
		transformedXpath = transformedXpath.replace(":"+placeHolderName,value);
		
	}

	@Override
	public String getTransformedXpath() {
		checkNotNull(transformedXpath,"You must bind some placeholder before call this method");
		String result = transformedXpath;
		transformedXpath = null;
		return result;
	}
}
