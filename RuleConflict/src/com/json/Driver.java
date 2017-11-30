package com.json;

import java.util.ArrayList;
import java.util.List;

import com.logger.FileLogger;
import com.rule.Builder;
import com.rule.Rule;

public class Driver {

	public static void main(String[] args) {
		Builder builder = new Builder();
		List<Rule> allRules = null; 
		String jsonExpression = null; 
		FileLogger log = FileLogger.instance();
		boolean failed = false;
		
		for(int i = 0; i < 2 & !failed; i++){
			jsonExpression = "boolean_expression" + (i + 1) + ".json";
			log.writeLog("Working on " + jsonExpression);
			List<Rule> current = builder.build(jsonExpression);
			
			// Should check for rule conflict here
			if(allRules == null){
				allRules = new ArrayList<>();
				allRules.addAll(current);
				continue;
			} 		
			
			for(Rule currentRule : current){
				for(Rule previousRule : allRules){
					failed = currentRule.checkConflict(previousRule);
					
					if(failed){
						System.out.println("Failed at " + currentRule.toString());
						continue;
					}
				}
			}
		}
		
		if(!failed){
			System.out.println("Rules added successfully.");
		}
		
		for(Rule rule : allRules){
			System.out.println(rule.toString());
		}
	}
}
