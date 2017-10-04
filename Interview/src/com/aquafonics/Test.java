package com.aquafonics;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Test {
	private HashMap<Rule, IntervalTree> map = null;
	private LinkedList<String> conflictList = null;
	private LinkedList<String> warningList = null;
			
	public Test() {
		this.map = new HashMap<>();
		this.conflictList = new LinkedList<>();
		this.warningList = new LinkedList<>();
	}

	/**
	 * Add the range interval to the interval tree
	 * 
	 * @param rule Rule containing (action, actuator) key
	 * @param interval range
	 * @param ruleId
	 * @param sensorId
	 */
	private void add(Rule rule, Interval interval, String ruleId, String sensorId) {
		IntervalTree it = null;
		try {
			it = (this.map.containsKey(rule)) ? this.map.get(rule) : new IntervalTree();
			it.put(interval, ruleId, sensorId);
			this.map.put(rule, it); 	
		} catch (RuleWarnings r) {
			this.warningList.add("WARNING: Sensor Id " + sensorId + " conflict's with: " + 
									r.getMessage().substring(1, r.getMessage().length()-1));
		} catch (RuleConflictException e) {
			this.conflictList.add("ERROR: Sensor Id" + sensorId + " conflict's with: " + e.getMessage());
		}
	}
	
	/**
	 * Helper function to retrieve opposite action 
	 * 
	 * @param action 
	 * @return opposite Action
	 */
	private String getOppositeAction(String action) {
		String oppositeAction = null;
		switch(action) {
		case "turn on" : oppositeAction = "turn off"; break;
		case "turn off": oppositeAction = "turn on"; break;
		}
		return oppositeAction;
	}
	
	/**
	 * Helper function to retrieve the opposite operator 
	 * 
	 * @param operator (<, <=, >, >=)
	 * @return opposite operator
	 */
	private String getOppositeOperator(String operator) {
		String oppositeOperator = null;
		switch(operator){
		case "<": oppositeOperator = ">="; break;
		case ">": oppositeOperator = "<="; break;
		case "<=": oppositeOperator = ">"; break;
		case ">=": oppositeOperator = "<"; break;
		}
		return oppositeOperator;
	}
	
	/**
	 * Helper function to create the interval based on operator and threshold value
	 * 
	 * @param operator (<, <=, >, >=) 
	 * @param thresholdValue
	 * @param flag true: while adding action
	 * 			  false: while adding opposite actions
	 * @return Interval object
	 */
	private Interval getInterval(String operator, int thresholdValue, boolean flag) {
		Interval newInterval = null;
		int min = 0, max = 150;
		switch (operator) {
		case "<":
			if(flag)
				newInterval = new Interval(min, thresholdValue - 1);
			else
				newInterval = new Interval(thresholdValue, max);
			break;
		case "<=":
			if(flag)
				newInterval = new Interval(min, thresholdValue);
			else
				newInterval = new Interval(thresholdValue + 1, max);
			break;
		case ">":
			if(flag)
				newInterval = new Interval(thresholdValue + 1, max);
			else
				newInterval = new Interval(min, thresholdValue);
			break;
		case ">=":
			if(flag)
				newInterval = new Interval(thresholdValue, max);
			else
				newInterval = new Interval(min, thresholdValue - 1);
			break;
		}
		return newInterval;
	}
	
	public static void main(String[] args) {
		Connection connection = null;
		Test t = new Test();
		Interval interval = null, oppositeInterval = null;
		String action = null, operator = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquafonics?autoReconnect=true&useSSL=false",
					"root", "welcome");

			String query = 
			"SELECT AC.Action_Name, AC.Action_Parameters, ST.Threshold_Value, OP.Operator_Name, ST.Rule_RID, ST.Sensor_ID"
			+ " FROM actions AS AC" 
			+ " JOIN STATEMENTS AS ST ON ST.rule_RID = AC.rule_RID" 
			+ " JOIN OPERATORS AS OP ON OP.oid = ST.oid"
			+ " WHERE AC.ATID in (5, 6);";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				// TODO: Remove code, using only for demo
				if(Integer.parseInt(rs.getString("rule_rid")) < 500) continue;
				
				action = rs.getString("Action_Name"); 
				operator = rs.getString("operator_name");
				
				// Not handling '=' & '!=' operators
				if(operator.equals("=") || operator.equals("!=")) continue;
				
				// Interval for action, actuator 
				interval = t.getInterval(operator, Integer.parseInt(rs.getString("threshold_value")), true);
				
				// Add action
				t.add(new Rule(action, rs.getString("Action_Parameters")), interval, 
						rs.getString("rule_rid"), rs.getString("sensor_id"));
				
				// Add Opposite Action
				String oppositeAction = t.getOppositeAction(rs.getString("Action_Name"));
				
				if(oppositeAction != null) {
					// Interval for opposite Action
					oppositeInterval = t.getInterval(t.getOppositeOperator(rs.getString("operator_name")),
							Integer.parseInt(rs.getString("threshold_value")), false);
				
					// Add opposite action
					t.add(new Rule(oppositeAction, rs.getString("Action_Parameters")), oppositeInterval, 
							rs.getString("rule_rid"), rs.getString("sensor_id"));
				} else {
					System.out.println("Received 'null' for " + action);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Print conflicts 
			if(t.conflictList.isEmpty()) {
				System.out.println("Rules have no conflict.");
			} else {
				for(String item : t.conflictList) {
					System.out.println(item);
				}
			} 
			
			// Print warnings
			if(t.warningList.isEmpty()) {
				System.out.println("Rules have no warnings.");
			} else {
				for(String item : t.warningList) {
					System.out.println(item);
				}
			}
			
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
