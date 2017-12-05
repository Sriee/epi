package com.json;

import java.util.Iterator;
import java.util.List;

import com.logger.FileLogger;
import com.parser.Container;
import com.parser.ContainerIterator;
import com.parser.Factory;
import com.rule.Builder;

public class Driver {

	public static void main(String[] args) {
		Factory factory = Factory.instance();
		Builder builder = new Builder(factory);
		String jsonExpression = null; 
		FileLogger log = FileLogger.instance();
		boolean failed = false;
		
		for(int i = 0; i < 2 && !failed; i++){
			jsonExpression = "boolean_expression" + (i + 1) + ".json";
			log.writeLog("Working on " + jsonExpression);
			List<Container> containerList = builder.build(jsonExpression);
//			ContainerIterator ci = new ContainerIterator();
			
			// Should check for rule conflict here
			for(Container lhs : containerList){
				
				if(builder.isRuleTableEmpty()){
					log.writeLog("Rule Table is empty. Adding first entry.");
					builder.add(lhs);
					continue; 
				}
				
				/*
		        Iterator<Container> iter = ci.iterator();
		        while(iter.hasNext() && !failed){
		        	failed = lhs.checkConflict(iter.next());
		        }
		        
	        	if(!failed){
	        		log.writeLog("Rule: " + lhs.getExpression() + " added sucessfully.");
	        		builder.add(lhs);
	        	}*/
			}
		}
		
		factory.close();
	}
}
