package com.github.hvasoares.pageobjects.aspects.macro;

public aspect ExecuteAfterDoAction {
	private boolean realizouAcao = false;
	
	pointcut checkIfActionIsExecuted(): execution(* *(..)) && !execution(* doAction()) && target(Macro);
	
	pointcut doActionFlag(): target(Macro) && execution(* doAction());
	
	after(): doActionFlag(){
		realizouAcao=true;
	}
		
	
	before(): checkIfActionIsExecuted() && @annotation(OnlyExecuteAfterDoAction){
		if(!realizouAcao)
			throw new RuntimeException("This method should be called after doAction method");
	}
}
