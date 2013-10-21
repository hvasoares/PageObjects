package com.github.pageobject.impl.mutability;

import java.util.List;

import com.github.pageobject.MutabilityCustomFieldFactory;

public interface FieldContainerI {

	void add(String alias, List<String> xpath,
			MutabilityCustomFieldFactory mutabilityCustomFieldFactory);

	void fill(String alias, String value, String[] args);

}
