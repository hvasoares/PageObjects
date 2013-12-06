package com.github.hvasoares.pageobjects.report;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

public class PathGeneratorTest {
	
	private Mockery context = new JUnit4Mockery(){{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	private ReportContext reportContext;
	private PathGenerator tested;		

	
	@Before
	public void setup(){
		reportContext = context.mock(ReportContext.class);
	}
	
	@Test
	public void testPathGeneration() {
		tested = new PathGenerator();
		final Description testDescription = context.mock(Description.class);
		final ReportSettings settings = context.mock(ReportSettings.class);
		
		final Calendar date = new GregorianCalendar(2013, 3, 7);
		context.checking( new Expectations(){{
			oneOf( reportContext ).getSettings(); will( returnValue (settings) );
			oneOf( settings ).getPath(); will( returnValue ( "C:\\test-date") );
			oneOf(reportContext).getCurrentStep(); will(returnValue(5));
			oneOf(reportContext).getExecutionDate(); will(returnValue( date.getTime() ) );
			atLeast(2).of(reportContext).getCurrentTest(); will( returnValue( testDescription ));
			
			atLeast(2).of( testDescription ).getDisplayName(); will( returnValue ("test with name x(com.github.pageobject.TestClass)") );
		}});
		
		// when
		String path = tested.generate(reportContext, "click");
		
		// then
		context.assertIsSatisfied();
		 
		Assert.assertThat( path, 
				Matchers.equalTo(  
						"C:\\test-date" + File.separator + "07-04-2013" + File.separator + "com.github.pageobject.TestClass" + File.separator + "5-test with name x-click.jpg") );
	}

}
