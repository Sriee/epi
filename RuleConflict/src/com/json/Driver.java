package com.json;

import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import com.bpodgursky.jbool_expressions.rules.RuleSet;
import com.bpodgursky.jbool_expressions.Expression;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import com.parser.*;
import com.rule.Rule;
import com.rule.Trigger;
import com.rule.Actuator;
import com.logger.FileLogger;

public class Driver {

	/* TODO: Trigger Id and actuator Id declared as static
	 * Should be retrieved from database
	 */
	private static int tId;
	private static int aId;
	
	public static void main(String[] args) throws IOException {
		// Configure gson
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Input.class, new InputDeserializer());
		Gson gson = gsonBuilder.create();

		FileLogger log = FileLogger.instance();
		Parser p = new Parser();
		Driver driver = new Driver();
		JsonReader reader = null;
		List<String> ruleTokens = null;
		List<Rule> rules = null;
		
		try {
			reader = new JsonReader(new FileReader(Paths.get(Paths.get(".").toAbsolutePath().toString(),
					"resources/boolean_expression.json").normalize().toString()));
			Input sample = gson.fromJson(reader, Input.class);
			log.writeLog(sample.toString());

			TreeNode root = p.buildAST(sample.getConditionalExpression());

			Expression<String> sop = RuleSet.toSop(p.buildExpression(root));
			String dnf = sop.toString();
			System.out.println("DNF: " + dnf);

			dnf = p.stripBrackets(dnf);
			ruleTokens = p.splitTokens(dnf, "|");
			rules = new ArrayList<>();
			
			for(String item : ruleTokens){
				System.out.print(item + " : ");
				List<String> conditions = p.splitTokens(item, "&");
				
				Rule temp = driver.builder(conditions, sample);
				
				rules.add(temp);
			}
			
			// Print rules
			for(int i = 0; i < rules.size(); i++){
				System.out.println("R" + i + " " + rules.get(i).toString());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private Rule builder(List<String> condition, Input input){
		Rule newRule = null;
		List<Trigger> triggerList = new ArrayList<>();
		List<Actuator> actuatorList = new ArrayList<>();
		Literals temp = null;
		Trigger tempTrigger = null;
		Actuator tempActuator = null;
		
		for(String c : condition){
			temp = input.getLiterals().get(c);
			
			triggerList.add(tempTrigger);
		}
		
		for(int k = 0; k < input.getActionCount(); k++){
			temp = input.getLiterals().get("a" + k);
			
			actuatorList.add(tempActuator);
		}
		
		newRule = new Rule(triggerList, null, actuatorList);
		return newRule; 
	} 
}
