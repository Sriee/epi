package com.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.logger.FileLogger;

public class Driver {

	public static void main(String[] args) throws IOException {
		// Configure gson
	    GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Input.class, new InputDeserializer());
	    Gson gson = gsonBuilder.create();
	    
	    FileLogger log = new FileLogger();
	    
	    JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader("/home/sriee/Git/epi/RuleConflict/resources/boolean_expression.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    Input sample = gson.fromJson(reader, Input.class);
	    log.writeLog(sample.toString());
	    
	    if(sample.getLiterals() != null){
	    	for(Map.Entry<String, Literals> entry : sample.getLiterals().entrySet()){
	    		log.writeLog(entry.getKey() + "\t" + entry.getValue().toString());
	    	}
	    } else {
	    	System.out.println("Null");
	    }
	}

}
