package com.github.pageobject.runner.resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.github.pageobject.runner.PageObjectSuite;

@RunWith(PageObjectSuite.class)
@SuiteClasses({SampleTest.class})
public class SamplePageObjectSuite {

}
