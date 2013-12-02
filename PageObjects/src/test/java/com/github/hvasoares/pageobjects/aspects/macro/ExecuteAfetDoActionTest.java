package com.github.hvasoares.pageobjects.aspects.macro;

import org.junit.Test;


public class ExecuteAfetDoActionTest {


	@Test(expected=RuntimeException.class)
	public void naoDeveDeixarChamarOMetodoAntesDeRealizarAcao(){
		TestMacro instance = new TestMacro();
		
		instance.annotatedMethod();
		
		instance.doAction();
	}
	
	@Test()
	public void shouldCallMethodAfterDoAction(){
		TestMacro instance = new TestMacro();
		instance.doAction();
		instance.annotatedMethod();
	} 
}
