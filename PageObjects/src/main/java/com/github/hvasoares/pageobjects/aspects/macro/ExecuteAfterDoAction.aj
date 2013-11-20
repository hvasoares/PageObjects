package com.github.hvasoares.pageobjects.aspects.macro;

public aspect ExecuteAfterDoAction {
	private boolean realizouAcao = false;
	
	pointcut checarRealizarAcaoFoiChamado(): execution(* *(..)) && !execution(* realizarAcao()) && target(Macro);
	
	pointcut realizouAcaoFlag(): execution(public void Macro.realizarAcao()) && target(Macro);
	
	after(): realizouAcaoFlag(){
		realizouAcao=true;
	}
		
	
	before(): checarRealizarAcaoFoiChamado() && @annotation(OnlyExecuteAfterDoAction){
		if(!realizouAcao)
			throw new RuntimeException("Esse método não pode ser chamado antes de realizarAcao()");
	}
}
