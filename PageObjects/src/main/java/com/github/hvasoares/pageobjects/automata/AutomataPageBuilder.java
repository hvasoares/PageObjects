package com.github.hvasoares.pageobjects.automata;

import com.github.hvasoares.pageobjects.Automata;
import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;

public class AutomataPageBuilder extends ProxyPageObjectBuilderAdapter {

	private AutomataImpl automata;
	private AutomataFieldFillerImpl fieldFiller;

	public AutomataPageBuilder(AutomataImpl automata, AutomataFieldFillerImpl automataFieldFiller) {
		this.automata = automata;
		this.fieldFiller = automataFieldFiller;
	}

	@Override
	public Automata automata() {
		startAutomata();
		return super.automata();
	}

	private void startAutomata() {
		if(super.automata()!=null)
			return;
		automata.setFieldFiller(fieldFiller);
		automata.setBuilder(getOuter());
		setAutomata(automata);
	}

	
}
