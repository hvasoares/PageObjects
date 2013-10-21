package com.github.pageobject.runner;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

abstract class Validator {
	public static void checkValidPageObjectSuite(Class<?> clazz) {
		SuiteClasses suite = clazz.getAnnotation(SuiteClasses.class);
		checkNotNull(suite,"The PageObjectSuite class must be annotated with org.junit.runners.Suite.SuiteClasses");
		for(Class<?> c : suite.value()){
			RunWith runWith = c.getAnnotation(RunWith.class);
			checkNotNull(runWith,"The class " + c.getCanonicalName() + " must be annotated with @RunWith");
			checkState(
					runWith.value().equals(PageObjectRunner.class) || runWith.value().equals(PageObjectSuite.class),
					"The RunWith must be annotated with PageObjectRunner or PageObjectSuite."
			);
			if(runWith.value().equals(PageObjectRunner.class)){
				checkPageObjectRepository(c);
			}
		}
	}

	private static void checkPageObjectRepository(Class<?> clazz) {
		checkState(clazz.isAnnotationPresent(PageObjectTest.class),
				"The class "
				+ clazz.getCanonicalName()
				+ " must be annotated with PageObjectTest!"
		);
	}
}
