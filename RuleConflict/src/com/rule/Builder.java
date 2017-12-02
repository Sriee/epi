package com.rule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import com.exceptions.InvalidExpressionSyntaxException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.json.Input;
import com.json.InputDeserializer;
import com.json.Literals;
import com.logger.FileLogger;
import com.parser.Parser;
import com.parser.TreeNode;

public class Builder {

	private GsonBuilder gsonBuilder;
	private Gson gson;
	private Parser parser;
	private FileLogger log;

	/* TODO: Trigger Id and actuator Id declared as static
	 * Should be retrieved from database
	 */
	private static int tId;
	private static int aId;

	public Builder(){
		this.log = FileLogger.instance();

		this.log.writeLog("Configuring Gson.");
		this.gsonBuilder = new GsonBuilder();
		this.gsonBuilder.registerTypeAdapter(Input.class, new InputDeserializer());
		this.gson = gsonBuilder.create();

		this.parser = new Parser();
	}

	public List<RuleHelper> build(String json) {
		String dnf = null;
		List<String> ruleTokens = null;
		List<RuleHelper> rules = null;

		try{
			JsonReader reader = new JsonReader(new FileReader(Paths.get(Paths.get(".").toAbsolutePath().toString(),
					json).normalize().toString()));
			this.log.writeLog("Deserializing Json content.");

			// Deserialize json content
			Input sample = gson.fromJson(reader, Input.class);
			this.log.writeLog(sample.toString());

			// Build Abstract Syntax Tree
			TreeNode root = this.parser.buildAST(sample.getConditionalExpression());

			Expression<String> expression = this.parser.buildExpression(root);
			Expression<String> sop = RuleSet.toSop(expression);
			this.log.writeLog("Expression: " + expression);
			this.log.writeLog("DNF form: " + sop.toString());

			dnf = this.parser.stripBrackets(dnf);
			ruleTokens = this.parser.splitTokens(dnf, "|");
			rules = new ArrayList<>();

			for(String item : ruleTokens){
				this.log.writeLog("Wrapping " + item);
				List<String> conditions = this.parser.splitTokens(item, "&");

				RuleHelper temp = this.wrapper(conditions, sample);
				temp.setExpression(item);
				this.log.writeLog(temp.toString());
				rules.add(temp);
			}
		} catch (FileNotFoundException e){
			this.log.writeLog("Could not load '" + json + "'");
		} catch (InvalidExpressionSyntaxException e) {
			e.printStackTrace();
		}
		return rules;
	}

	private RuleHelper wrapper(List<String> condition, Input input){
		RuleHelper newRule = null;
		List<Trigger> triggerList = new ArrayList<>();
		List<Actuator> actuatorList = new ArrayList<>();
		Literals temp = null;
		Trigger tempTrigger = null;
		Actuator tempActuator = null;

		for(String c : condition){
			temp = input.getLiterals().get(c);
			tempTrigger = new Trigger(++tId, temp.getName(), temp.getOperator(), temp.getValue());
			triggerList.add(tempTrigger);
		}

		for(int k = 0; k < input.getActionCount(); k++){
			temp = input.getLiterals().get("a" + (k + 1));
			tempActuator = new Actuator(++aId, temp.getName(), temp.getOperator(), temp.getValue());
			actuatorList.add(tempActuator);
		}

		newRule = new RuleHelper(triggerList, null, actuatorList);
		return newRule; 
	} 
}
