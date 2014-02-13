package com.github.hvasoares.pageobjects.automata;

import com.github.hvasoares.pageobjects.impl.ProxyPageObjectBuilderAdapter;

public abstract class AutomataFactory {
	public static ProxyPageObjectBuilderAdapter create(){
		return new AutomataPageBuilder(new AutomataImpl());
	}
}
