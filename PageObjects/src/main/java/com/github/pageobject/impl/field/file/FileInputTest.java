package com.github.pageobject.impl.field.file;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import com.github.pageobject.impl.browser.Browser;

public class FileInputTest {

	private FileInputField inst;
	private Browser browser;
	private Mockery ctx;
	private PathGenerator pathGenerator;

	@Test
	public void shouldLoadFileAndCopyIt() {
		ctx = new Mockery();
		browser = ctx.mock(Browser.class);
		pathGenerator = ctx.mock(PathGenerator.class);
		
		inst = new FileInputField("someAlias","someXpath",browser,pathGenerator);
		
		ctx.checking(new Expectations(){{
			oneOf(pathGenerator).generateFromResourceName("recursoArquivo.docx");
			will(returnValue("somePath"));
			oneOf(browser).fill("someXpath","somePath");
		}});
		
		inst.fill("recursoArquivo.docx");
		
		ctx.assertIsSatisfied();
	}
	

}
