package com.github.hvasoares.pageobjects.impl;

import org.openqa.selenium.WebDriver;

import com.github.hvasoares.pageobjects.Automata;
import com.github.hvasoares.pageobjects.Mutability;
import com.github.hvasoares.pageobjects.PageObjectBuilder;

public abstract class PageObjectBuilderSymbolTable implements PageObjectBuilder,WaitingStartFactory  {
	private static Readability readability;
	private static Mutability mutability;
	private static Automata automata;

	@Override
	public final Readability readability(){
		return readability;
	}

	protected WebDriver getWebDriver(){
		return null;
	}
	
	protected final void setReadability(Readability value){
		readability = value;
	}
	
	public final Mutability mutability(){
		return mutability;
	}

	protected void setMutability(Mutability value) {
		mutability = value;
	}
	
	protected void setAutomata(Automata value){
		automata = value;
	}

	@Override
	public Automata automata() {
		return automata;
	}

}
