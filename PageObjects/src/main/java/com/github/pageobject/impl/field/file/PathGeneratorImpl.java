package com.github.pageobject.impl.field.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class PathGeneratorImpl implements PathGenerator{
	@Override
	public String generateFromResourceName(String resourceName) {
		String[] resourceSplited = resourceName.split("\\.");
		String name = resourceName.split("\\.")[resourceSplited.length-2];
		String extension = resourceName.split("\\.")[resourceSplited.length-1];
		try {
			File temp;
			FileOutputStream out = new FileOutputStream(
					temp =File.createTempFile(name,"."+extension)
			);
			InputStream is = this.getClass()
					.getClassLoader()
					.getResourceAsStream(resourceName);
			
			IOUtils.copy(is,out);
			out.close();
			return temp.getAbsolutePath();
			
		} catch (IOException e) {
			throw new RuntimeException("Some error occurred while trying to create" +
					" a temp file for " +resourceName,e);
		}
	}
}
