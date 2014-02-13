package com.github.hvasoares.pageobjects.automata;

import com.github.hvasoares.pageobjects.Automata;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;

public class AutomataPageBuilder extends ProxyPageObjectBuilderAdapter {

	private AutomataImpl automata;

	public AutomataPageBuilder(AutomataImpl automata) {
		this.automata = automata;
	}

	@Override
	public Automata automata() {
		startAutomata();
		return super.automata();
	}

	private void startAutomata() {
		if(super.automata()!=null)
			return;
		automata.setBuilder(getOuter());
		setAutomata(automata);
	}

	
}
