package com.neu.info7255.demo;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.neu.info7255.demo.validation.ValidationUtils;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Richademo1Application {

	public static void main(String[] args) throws IOException, ProcessingException {
		
		File schemaFile = new File("/Users/richabhatia/Downloads/richademo1/src/main/resources/static/usecase-schema.json");
		File jsonFile = new File("/Users/richabhatia/Downloads/richademo1/src/main/resources/static/usecase.json");
		 if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
		    	System.out.println("Valid!");
		    }else{
		    	System.out.println("NOT valid!");
		    }
		
		
		
		SpringApplication.run(Richademo1Application.class, args);
	}
}
