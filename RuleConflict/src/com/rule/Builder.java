package com.rule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.rules.RuleSet;
import com.entity.Action;
import com.entity.Actuator;
import com.entity.Environment;
import com.entity.Link;
import com.entity.LinkPK;
import com.entity.Rule;
import com.entity.Sensor;
import com.entity.Trigger;
import com.entity.Type;
import com.exceptions.InvalidExpressionSyntaxException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.json.Input;
import com.json.InputDeserializer;
import com.json.Literals;
import com.json.LogicalOperator;
import com.logger.FileLogger;
import com.parser.Container;
import com.parser.Factory;
import com.parser.Parser;
import com.parser.TreeNode;

public class Builder {

	private GsonBuilder gsonBuilder;
	private Gson gson;
	private Parser parser;
	private Factory factory;
	private FileLogger log;

	public Builder(Factory factory) {
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

		try {
			JsonReader reader = new JsonReader(new FileReader(
					Paths.get(Paths.get(".").toAbsolutePath().toString(), "resources/" + json).normalize().toString()));
			this.log.writeLog("Deserializing Json content.");

			// Deserialize json content
			Input sample = gson.fromJson(reader, Input.class);
			this.log.writeLog(sample.toString());

			if (sample.getConditionCount() != 1) {
				// Build Abstract Syntax Tree
				TreeNode root = this.parser.buildAST(sample.getConditionalExpression());

				Expression<String> expression = this.parser.buildExpression(root);
				Expression<String> sop = RuleSet.toSop(expression);
				this.log.writeLog("Expression: " + expression);
				this.log.writeLog("DNF form: " + sop.toString());

				dnf = this.parser.stripBrackets(sop.toString());
				ruleTokens = this.parser.splitTokens(dnf, "|");
			} else {
				this.log.writeLog("Skipping DNF conversion.");
				ruleTokens = new ArrayList<>();
				ruleTokens.add(this.parser.stripBrackets(sample.getConditionalExpression()));
			}
			rules = new ArrayList<>();

			for (String item : ruleTokens) {
				this.log.writeLog("Wrapping " + item + " to a container.");
				List<String> conditions = this.parser.splitTokens(item, "&");

				Container temp = this.wrapper(conditions, sample);
				temp.setExpression(item);

				rules.add(temp);
			}
		} catch (FileNotFoundException e) {
			this.log.writeLog("Could not load '" + json + "'");
		} catch (InvalidExpressionSyntaxException e) {
			e.printStackTrace();
		}
		return rules;
	}

	public void add(Container container) {
		long count = 0;
		String ruleName = null;
		Link newLink = null;
		Session session = this.factory.getFactory().openSession();
		session.beginTransaction();

		count = (long) session.createQuery("SELECT COUNT(*) FROM Rule").getSingleResult();
		ruleName = "R" + (count + 1);

		// Save rule
		Rule toBeInserted = new Rule(null, ruleName);
		session.save(toBeInserted);

		count = (long) session.createQuery("SELECT COUNT(*) FROM Link").getSingleResult();

		// Save Link for Triggers
		for (Trigger trigger : container.getTriggerList()) {
			newLink = new Link(new LinkPK(++count, toBeInserted.getId()), Type.TRIGGER, trigger.getId().intValue());
			session.save(newLink);
		}

		// Save Link for Environment
		for (Environment environment : container.getEnvironmentList()) {
			newLink = new Link(new LinkPK(++count, toBeInserted.getId()), Type.ENVIRONMENT,
					environment.getId().intValue());
			session.save(newLink);
		}

		// Save Link for Actuators
		for (Action action : container.getActionList()) {
			newLink = new Link(new LinkPK(++count, toBeInserted.getId()), Type.ACTION, action.getId().intValue());
			session.save(newLink);
		}

		session.getTransaction().commit();
		session.close();
	}

	public boolean isRuleTableEmpty() {
		long count = 0;
		Session session = this.factory.getFactory().openSession();
		session.beginTransaction();

		count = (long) session.createQuery("select count(*) from Rule").getSingleResult();
		session.close();
		return count == 0;
	}

	public Long getTriggerId(Literals literal, Sensor sensor) {
		Trigger toBeInserted = null;
		Long id = null;
		Session session = this.factory.getFactory().openSession();
		session.beginTransaction();
		List<Trigger> aTrigger = session
				.createQuery("FROM Trigger WHERE sensorId = :id" + " AND operator = :operator AND value = :value",
						Trigger.class)
				.setParameter("id", sensor).setParameter("operator", literal.getOperator())
				.setParameter("value", literal.getValue()).getResultList();

		if (aTrigger.isEmpty()) { // Create a new Trigger if empty
			this.log.writeLog("Creating new Trigger for " + literal.getName());
			toBeInserted = new Trigger(null, literal.getName(), sensor, literal.getOperator(), literal.getValue(), -1);
			session.save(toBeInserted);
			id = toBeInserted.getId();
			session.getTransaction().commit();
		} else {
			id = aTrigger.get(0).getId();
		}
		session.close();
		return id;
	}

	public Environment getEnvironment(Literals literal) {
		Environment toBeInserted = null;
		Session session = this.factory.getFactory().openSession();
		session.beginTransaction();
		String prev = literal.getOperator().toString() + ";" + Integer.toString(literal.getValue());
		String next = this.getNextState(literal.getOperator(), literal.getValue());
		List<Environment> aEnvironment = session
				.createQuery("FROM Environment WHERE name = :name" + " AND previousState = :prev AND nextState = :next",
						Environment.class)
				.setParameter("name", literal.getName()).setParameter("prev", prev).setParameter("next", next)
				.getResultList();

		if (aEnvironment.isEmpty()) { // Create a new Environment if empty
			this.log.writeLog("Creating new Environment for " + literal.getName());

			toBeInserted = new Environment(null, literal.getName(), prev, next);
			session.save(toBeInserted);
			session.getTransaction().commit();
		} else {
			toBeInserted = aEnvironment.get(0);
		}
		session.close();
		return toBeInserted;
	}

	public Long getActionId(Literals literal, Actuator actuator) {
		Action toBeInserted = null;
		Long id = null;
		Session session = this.factory.getFactory().openSession();
		session.beginTransaction();
		List<Action> aAction = session
				.createQuery("FROM Action WHERE actuatorId = :id" + " AND operator = :operator AND value = :value",
						Action.class)
				.setParameter("id", actuator).setParameter("operator", literal.getOperator())
				.setParameter("value", literal.getValue()).getResultList();

		if (aAction.isEmpty()) { // Create a new Trigger if empty
			this.log.writeLog("Creating new Action for " + literal.getName());
			toBeInserted = new Action(null, literal.getName(), actuator, literal.getOperator(), literal.getValue(), -1);
			session.save(toBeInserted);
			id = toBeInserted.getId();
			session.getTransaction().commit();
		} else {
			id = aAction.get(0).getId();
		}
		session.close();
		return id;
	}

	private Container wrapper(List<String> condition, Input input) {
		List<Trigger> triggerList = new ArrayList<>();
		List<Action> actionList = new ArrayList<>();
		List<Environment> environmentList = new ArrayList<>();
		Literals temp = null;
		Trigger tempTrigger = null;
		Action tempAction = null;
		Actuator tempActuator = null;
		Sensor tempSensor = null;

		Factory factory = Factory.instance();

		for (String c : condition) {
			temp = input.getLiterals().get(c);
			Session session = factory.getFactory().openSession();
			session.beginTransaction();

			tempSensor = (Sensor) session.createQuery("FROM Sensor WHERE id = " + temp.getId()).getSingleResult();
			session.close();

			tempTrigger = new Trigger(this.getTriggerId(temp, tempSensor), temp.getName(), tempSensor,
					temp.getOperator(), temp.getValue(), -1);

			triggerList.add(tempTrigger);
			environmentList.add(this.getEnvironment(temp));
		}

		// Log trigger list
		this.log.writeLog("Trigger List:");
		this.log.writeLog(triggerList.toString());

		// log environment list
		this.log.writeLog("Environment List:");
		this.log.writeLog(environmentList.toString());

		for (int k = 0; k < input.getActionCount(); k++) {
			temp = input.getLiterals().get("a" + (k + 1));
			Session session = factory.getFactory().openSession();
			session.beginTransaction();
			tempActuator = (Actuator) session.createQuery("FROM Actuator WHERE serial_id = :id ")
					.setParameter("id", temp.getId()).getSingleResult();
			session.close();

			tempAction = new Action(this.getActionId(temp, tempActuator), temp.getName(), tempActuator,
					temp.getOperator(), temp.getValue());

			actionList.add(tempAction);
		}
		this.log.writeLog("Action List:");
		this.log.writeLog(actionList.toString());

		return new Container(null, null, triggerList, environmentList, actionList);
	}

	/**
	 * Helper function to retrieve the opposite operator
	 * 
	 * @param operator
	 *            (<, <=, >, >=)
	 * @return opposite operator
	 */
	private LogicalOperator getOppositeOperator(LogicalOperator operator) {
		LogicalOperator oppositeOperator = null;
		switch (operator) {
		case LESSER_THAN:
			oppositeOperator = LogicalOperator.GREATER_THAN_OR_EQUAL;
			break;
		case GREATER_THAN:
			oppositeOperator = LogicalOperator.LESSER_THAN_OR_EQUAL;
			break;
		case LESSER_THAN_OR_EQUAL:
			oppositeOperator = LogicalOperator.GREATER_THAN;
			break;
		case GREATER_THAN_OR_EQUAL:
			oppositeOperator = LogicalOperator.LESSER_THAN;
			break;
		case EQUAL:
			oppositeOperator = LogicalOperator.NOT_EQUAL;
			break;
		case NOT_EQUAL:
			oppositeOperator = LogicalOperator.EQUAL;
			break;
		}
		return oppositeOperator;
	}

	private String getNextState(LogicalOperator operator, int value) {
		return this.getOppositeOperator(operator).toString() + ";" + Integer.toString(value);
	}
}
