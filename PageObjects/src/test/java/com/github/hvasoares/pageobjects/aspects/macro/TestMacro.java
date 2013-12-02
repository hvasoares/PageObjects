package com.github.hvasoares.pageobjects.aspects.macro;


public class TestMacro implements Macro{
	
	@OnlyExecuteAfterDoAction
	public void annotatedMethod(){
		
	}
	
	@Override
	public void doAction() {
		
	}
	
} 
