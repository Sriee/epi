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
		
		for(int i = 0; i < 2 && !failed; i++){
			jsonExpression = "boolean_expression" + (i + 1) + ".json";
			log.writeLog("Working on " + jsonExpression);
			List<Rule> current = builder.build(jsonExpression);
			
			// Should check for rule conflict here
			if(allRules == null){
				allRules = new ArrayList<>();
				allRules.addAll(current);
				continue;
			} 		
			
			for(int j = 0; j < current.size() && !failed; j++){
				Rule currentRule = current.get(j);
				for(int k = 0; k < allRules.size() && !failed;k++){
					Rule previousRule = allRules.get(k);
					failed = currentRule.checkConflict(previousRule);
					
					if(failed){
						System.out.println("Failed at " + currentRule.toString());
						continue;
					}
				}
			}
			
			if(!failed){ 
				allRules.addAll(current); 
				System.out.println("Rules added successfully for '" + jsonExpression + "'");
			}
		}
	}
}
