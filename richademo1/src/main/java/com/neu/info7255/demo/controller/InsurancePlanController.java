package com.neu.info7255.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.jayway.jsonpath.JsonPath;
import com.neu.info7255.demo.InsurancePlan;
//import com.neu.info7255.demo.Person;
//import com.neu.info7255.demo.config.MyRedisConnection;
//import com.neu.info7255.demo.validation.ValidateJSON;
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

@RestController
@RequestMapping(value = "/insuranceplans")
public class InsurancePlanController {
	
	
	private CrudRepository<InsurancePlan, String> pRepository;
	File schemaFile = new File("/Users/richabhatia/Downloads/richademo1/src/main/resources/static/usecase-schema.json");
	
	
	@Autowired
	public InsurancePlanController(CrudRepository<InsurancePlan, String> pRepository){
		
		this.pRepository = pRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<InsurancePlan> plans(){
		
		
		return pRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public InsurancePlan add(@RequestBody @Valid InsurancePlan plan){
		
		return pRepository.save(plan);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public InsurancePlan update(@RequestBody @Valid InsurancePlan plan){
		System.out.println("post request");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(plan);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File jsonFile = null;
		FileWriter jsonFileWriter = null;
		try{
			
			
			 jsonFile = File.createTempFile("test", ".json");
			 jsonFileWriter = new FileWriter(jsonFile);
			jsonFileWriter.write(json);
			 jsonFileWriter.close();
		}catch(Exception e){
			System.out.println("exception occurred :" + e.getMessage());
			
		}finally{
			
		}
		
		
		 try {
			if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
			    	System.out.println("Valid!");
			    }else{
			    	System.out.println("NOT valid!");
			    }
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("printing"+plan.getLinkedPlanServicesList().get(0));
		return pRepository.save(plan);
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*	public static final String REF = "ref_plan";
	Map<String, String> mapOfJson = null;
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String WORK_QUEUE = "WORK_QUEUE";
	public static final String WORK_QUEUE1 = "WORK_QUEUE1";
	public static final String PARENT = "_parent";
	
	
	@RequestMapping(value = "/{resource}", method = RequestMethod.POST)
	@ResponseBody
	public String postPlan(@RequestBody JSONObject postedPlan, @PathVariable String resource,HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		
		
		
		Boolean isJSONValid = ValidateJSON.isJSONValid(postedPlan.toJSONString());
		if (isJSONValid == false) {
			return "JSON payload invalidated against schema";
		} else {
			Jedis jedis = MyRedisConnection.getConnection();
			Map<String, Object> mapOfJSON = null;
			try{
				mapOfJSON= new Gson().fromJson(postedPlan.toJSONString(),
						new TypeToken<Map<String, Object>>() {
						}.getType());
				
			}catch(Exception e){
				
				System.out.println("exception occured: " + e.getMessage());
			}
			
			
			System.out.println("test");
			
			//String planId = parseJson(mapOfJSON, jedis);
			String plan_uuid = "plan_" + UUID.randomUUID().toString();
			mapOfJson = new HashMap();
			Date date = new Date();
			mapOfJSON.put("createdAt", dateFormat.format(date));
			mapOfJSON.put("updatedAt", dateFormat.format(date));
			String planId = parseJson(mapOfJSON, jedis,plan_uuid,mapOfJSON,plan_uuid);

			System.out.println("planId created is:" + planId);
			fillQueue(planId,jedis);
			
		
			return "planId == > " + planId;

		}

	}
	
	@RequestMapping(value = "/plan/{planId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Object getPlan(@PathVariable String planId,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		JSONObject acc = new JSONObject();
		
		
		Jedis jedis = MyRedisConnection.getConnection();
		
		if(!jedis.exists(planId)){
			
			JSONObject errorMessage = new JSONObject();
			errorMessage.put("error", "Plan does not exist");
			return errorMessage;
		}
		
		
		
		JSONObject rootJson = new JSONObject();
		
		JSONObject plan = (JSONObject) recreateJSON(planId, rootJson, jedis,planId);
		
		try{
			String jsonPath = request.getParameter("path");
			if(jsonPath != null){
				System.out.println(jsonPath);
				Object queriedJson = JsonPath.read(plan, jsonPath);
				return queriedJson;
			}else{
				return plan;
			}
		}catch(Exception e){
			return new JSONObject();
		}
		
		

	}
	
public Object recreateJSON(String id, Object object,Jedis jedis, String planId_root){
		
		
		if(jedis.type(id).equalsIgnoreCase("hash")){
			Map<String, String> retrieveMap = jedis.hgetAll(id);
			for (String keyMap : retrieveMap.keySet()) {
				String value = retrieveMap.get(keyMap);
				if((value.contains(planId_root) ||value.contains(REF)) && jedis.type(value).equals("hash")){
					JSONObject newObject = new JSONObject();
					JSONObject fillObject = (JSONObject) object;
					fillObject.put(keyMap,newObject);
					recreateJSON(value,newObject,jedis,planId_root);
				}else if((value.contains(planId_root) ||value.contains(REF)) && jedis.type(value).equals("set")){
					JSONArray newArray = new JSONArray();
					JSONObject fillObject = (JSONObject) object;
					fillObject.put(keyMap,newArray);
					recreateJSON(value,newArray,jedis,planId_root);
				}
				else{
					JSONObject fillObject = (JSONObject) object;
					fillObject.put(keyMap, value);
				}
			}
			
		}else if(jedis.type(id).equalsIgnoreCase("set")){
			
			Set<String> set = jedis.smembers(id);
			List<String> list = new ArrayList();;
			list.addAll(set);
			int size = list.size();
			JSONArray fillObject = (JSONArray) object;
			for(int i=0;i<size;i++){
				String value = list.get(i);
				if((value.contains(planId_root) ||value.contains(REF)) && jedis.type(value).equals("hash")){
					JSONObject newObject = new JSONObject();
					fillObject.add(newObject);
					recreateJSON(value,newObject,jedis,planId_root);
				}else if((value.contains(planId_root) ||value.contains(REF)) && jedis.type(value).equals("set")){
					JSONArray newArray = new JSONArray();
					fillObject.add(newArray);
					recreateJSON(value,newArray,jedis,planId_root);
				}else{
					fillObject.add(value);
				}
				
			}
			
		}
		
		return object;
	}
	
public String parseJson(Map postedPlan, Jedis jedis,String planId,Map hm,String planId_root) {

	//String planId = "plan_" + UUID.randomUUID().toString();
	
	Set keys = (Set) postedPlan.keySet();
	Iterator itr = keys.iterator();
	while (itr.hasNext()) {
		String key = (String) itr.next();
		if (postedPlan.get(key) instanceof Map ) {
			String uuid = REF+buildKey((Map)postedPlan.get(key),jedis,planId_root,planId);
			if(planId_root.equalsIgnoreCase("PATCH")){
				jedis.lpush(WORK_QUEUE, uuid+"_parent");
			}else{
				jedis.lpush(WORK_QUEUE, uuid);
			}
			
			hm.put(key, uuid);
			Map hm1 = new HashMap();
			parseJson((Map)postedPlan.get(key),jedis,uuid,hm1,planId_root);
			//jedis.set(planId + "_Map_2_" + key, postedPlan.get(key).toString());
		} else if (postedPlan.get(key) instanceof ArrayList) {

//			String uuid = planId_root+"_"+cal.getTimeInMillis();
			String uuid = planId_root+"_"+key;
			hm.put(key, uuid);
			Map hm_array = new HashMap();
			for (Object value : (ArrayList) postedPlan.get(key)) {
				if(value instanceof Map){
					
					String uuidInArray = REF+buildKey((Map) value,jedis,planId_root,planId);
					if(planId_root.equalsIgnoreCase("PATCH")){
						jedis.lpush(WORK_QUEUE, uuidInArray+"_parent");
					}else{
						jedis.lpush(WORK_QUEUE, uuidInArray);
					}
					jedis.sadd(uuid, uuidInArray);
					Map hm1 = new HashMap();
					parseJson((Map) value,jedis,uuidInArray,hm1,planId_root);
					
				}else{
					jedis.sadd(uuid, value.toString());
				}
				
				
			}

		} else {

			hm.put(key, postedPlan.get(key).toString());

		}
	}

	jedis.hmset(planId, hm);

	return planId;
}

public void fillQueue(String planId,Jedis jedis){
	
	jedis.lpush(WORK_QUEUE1, planId);
	List<String> list = jedis.lrange(WORK_QUEUE, 0 ,100); 
      int size = list.size();
      for(int i = (size-1); i>=0; i--) { 
         jedis.lpush(WORK_QUEUE1, list.get(i)); 
      }
      
      jedis.del(WORK_QUEUE);
}


public String buildKey(Map json,Jedis jedis,String planID_root,String patchId){

	Set keys = (Set) json.keySet();
	Iterator itr = keys.iterator();
	String type = null;
	String id = null;
	while (itr.hasNext()) {
		String key = (String) itr.next();
		if(key.equals("_type")){
			type = String.valueOf(json.get("_type"));
		}else if(key.equals("_id")){
			
			//id =(int) ((Double)json.get("_id")).doubleValue();
			
			id = String.valueOf(json.get("_id"));
		}
	}

	String time = String.valueOf((System.currentTimeMillis()));
	if(type==null && id == null){
		type = time;
		id = time;
	}else if(type == null && id != null){
		type = time;
	}else if(type != null && id == null){
		id = time;
	}
	if(planID_root != "" && !planID_root.equalsIgnoreCase("PATCH")){
		jedis.sadd(REF+"_"+type+"_"+id+PARENT,planID_root);
	}
	else if(planID_root.equalsIgnoreCase("PATCH")){
		Set<String> set = jedis.smembers(patchId+PARENT);
		List<String> list = new ArrayList();
		list.addAll(set);
		int size = list.size();
		for(int i = 0; i< size; i++) { 
			jedis.sadd(REF+"_"+type+"_"+id+PARENT,list.get(i));

	      }
	}
	
	
	return "_"+type+"_"+id;
}*/
	

}
