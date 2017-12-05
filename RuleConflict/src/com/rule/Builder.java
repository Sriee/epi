package com.rule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import com.entity.*;
import com.exceptions.InvalidExpressionSyntaxException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.json.Input;
import com.json.InputDeserializer;
import com.json.Literals;
import com.logger.FileLogger;
import com.parser.*;

public class Builder {

	private GsonBuilder gsonBuilder;
	private Gson gson;
	private Parser parser;
	private Factory factory;
	private FileLogger log;

	public Builder(Factory factory){
		this.log = FileLogger.instance();
		this.factory = factory;
		this.log.writeLog("Configuring Gson.");
		this.gsonBuilder = new GsonBuilder();
		this.gsonBuilder.registerTypeAdapter(Input.class, new InputDeserializer());
		this.gson = gsonBuilder.create();

		this.parser = new Parser();
	}

	public List<Container> build(String json) {
		String dnf = null;
		List<String> ruleTokens = null;
		List<Container> rules = null;

		try{
			JsonReader reader = new JsonReader(new FileReader(Paths.get(Paths.get(".").toAbsolutePath().toString(), "resources/" + 
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

			dnf = this.parser.stripBrackets(sop.toString());
			ruleTokens = this.parser.splitTokens(dnf, "|");
			rules = new ArrayList<>();

			for(String item : ruleTokens){
				this.log.writeLog("Wrapping " + item + " to a container.");
				List<String> conditions = this.parser.splitTokens(item, "&");

				Container temp = this.wrapper(conditions, sample);
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
	
	public void add(Container container){
		
	}
	
	public boolean isRuleTableEmpty(){
		long count = 0;
		Session session = this.factory.getCurrentSession();
		session.beginTransaction();
		
		count = (long) session.createQuery("select count(*) from Rule").getSingleResult();
		session.close();
		return count == 0;
	}
	
	private Container wrapper(List<String> condition, Input input){
		List<Trigger> triggerList = new ArrayList<>();
		List<Action> actionList = new ArrayList<>();
		Literals temp = null;
		Trigger tempTrigger = null;
		Action tempAction = null;
		Actuator tempActuator = null;
		Sensor tempSensor = null;
		Factory factory = Factory.instance();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		for(String c : condition){
			temp = input.getLiterals().get(c);
			tempSensor = (Sensor) session.createQuery("FROM Sensor WHERE id = " + temp.getId()).getSingleResult();
			tempTrigger = new Trigger(Long.parseLong(temp.getId()), temp.getName(), tempSensor, temp.getOperator(), temp.getValue(), -1);
			triggerList.add(tempTrigger);
		}
		for(int k = 0; k < input.getActionCount(); k++){
			temp = input.getLiterals().get("a" + (k + 1));
			tempActuator = (Actuator) session.createQuery("from Actuator WHERE serial_id = :id ").setParameter("id", temp.getId()).getSingleResult();
			tempAction = new Action(temp.getName(), tempActuator, temp.getOperator(), temp.getValue());
			actionList.add(tempAction);
		}
		
		session.close();
		
		return new Container(null, null, triggerList, null, actionList); 
	} 
}
