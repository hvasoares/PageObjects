package com.github.pageobject.impl.field.select;

import com.github.pageobject.impl.field.Select;

public abstract class SelectField implements Select{
	public static Select createFixedSelect(String alias,String rootXpath,String ... possibleOptions){
		return new FixedChoice(
				createFluidSelect(alias, rootXpath), 
				possibleOptions
		);
	}
	public static Select createFluidSelect(String alias,String rootXpath){
		return new FluidChoice(alias,rootXpath);
	}
}
