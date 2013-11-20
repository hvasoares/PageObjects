package com.github.hvasoares.pageobjects.impl.mutability;

import java.util.List;

import com.github.hvasoares.pageobjects.MutabilityCustomFieldFactory;

public interface FieldContainerI {

	void add(String alias, List<String> xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory);

	void fill(String alias, String value, String[] args);

}
