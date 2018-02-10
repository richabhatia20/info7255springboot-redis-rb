package com.neu.info7255.demo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class StringLiterals {
	
	public static String getSchemaString() throws IOException{
	File schemaFile = new File("/Users/richabhatia/Downloads/richademo1/src/main/resources/static/usecase-schema.json");
	
	
	String schema = FileUtils.readFileToString(schemaFile);
	System.out.println("schema fetch");
	return schema;
	}

}
