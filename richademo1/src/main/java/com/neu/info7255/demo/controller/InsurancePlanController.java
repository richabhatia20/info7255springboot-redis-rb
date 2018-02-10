package com.neu.info7255.demo.controller;

//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.info7255.demo.InsurancePlan;
import com.neu.info7255.demo.LinkedPlanServices;
import com.neu.info7255.demo.StringLiterals;
import com.neu.info7255.demo.validation.ValidationUtils;



//import redis.clients.jedis.Jedis;

//import org.json.simple.JSONObject;
//import org.apache.http.impl.io.SocketOutputBuffer;
//import org.json.simple.JSONArray;

//import com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/insuranceplans")
public class InsurancePlanController {
	
	
	private CrudRepository<InsurancePlan, String> pRepository;
	
	
	@Autowired
	public InsurancePlanController(CrudRepository<InsurancePlan, String> pRepository){
		
		this.pRepository = pRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<InsurancePlan> plans(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException{
		String etag = generateEtagsForResponse(new Date().toString());
		response.setHeader("eTag", etag);
		
		return pRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public InsurancePlan add(@RequestBody @Valid InsurancePlan plan,HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException{
		String etag = generateEtagsForResponse(new Date().toString());
		response.setHeader("eTag", etag);
		return pRepository.save(plan);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public InsurancePlan update(@RequestBody @Valid InsurancePlan plan,HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException{
		String planObjID = plan.getObjectId()+"-"+UUID.randomUUID();
		String planCostShareObjID = plan.getPlanCostShares().getObjectId()+ "-"+ UUID.randomUUID();
		
		plan.setObjectId(planObjID);
		plan.getPlanCostShares().setObjectId(planCostShareObjID);
		
		plan.createLinkedPlanServicesList();
		
		LinkedPlanServices lps = plan.getLinkedPlanServicesList().get(0);
		System.out.println("objID: "+lps.getObjectId());
		
		System.out.println("post request");
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		String json = null;
		try {
			json = mapper.writeValueAsString(plan);
			Map<String, Object> myMap = new Gson().fromJson(json, new TypeToken<Map<String,Object>>(){}.getType()) ;
			System.out.println("map using type token: " +myMap);
			//pRepository.save(myMap);
			
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			System.out.println("error occured");
			e1.printStackTrace();
		}
		
		String schemaFile = null;
		try {
			schemaFile = StringLiterals.getSchemaString();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("error occured");
			e1.printStackTrace();
		}
		String etag = generateEtagsForResponse(new Date().toString());
		response.setHeader("eTag", etag);
		 try {
			if (ValidationUtils.isJsonValid(schemaFile, json)){
			    	System.out.println("Valid!");
			    	return pRepository.save(plan);
			    }else{
			    	System.out.println("NOT valid!");
			    	InsurancePlan plan2 = new InsurancePlan();
			    	return plan2;
			    }
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("printing"+plan.getLinkedPlanServicesList().get(0));
		//return pRepository.save(plan);
		 return null;
	}
	
	@RequestMapping(value = "/{planType:.+}", method = RequestMethod.GET)
    public InsurancePlan getById(@PathVariable String planType) {
		System.out.println("get plan type:" + planType);
        return pRepository.findOne(planType);
    }

    @RequestMapping(value = "/{planType:.+}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String planType) {
    	System.out.println("delete plan type:" + planType);
        pRepository.delete(planType);
    }

	public String generateEtagsForResponse(String str) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
		
	}

}
