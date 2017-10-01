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
	 * TODO: Add call to check with opposite actions
	 * @param action
	 * @param actuator
	 * @param thresholdValue
	 * @param operator
	 * @param ruleId
	 * @param sensorId
	 */
	private void add(String action, String actuator, int thresholdValue, String operator, String ruleId, String sensorId) {
		int min = 0, max = 150;
		Rule rule = null;
		IntervalTree it = null;
		String oppositeAction = getOppositeAction(action);
		
		if(oppositeAction == null) {
			System.out.println("Received 'null' for " + action);
		}
		try {
			rule = new Rule(action, actuator);
			it = (this.map.containsKey(rule)) ? this.map.get(rule) : new IntervalTree();
			switch (operator) {
			case "<":
				it.put(min, thresholdValue - 1, ruleId, sensorId);
				this.map.put(rule, it);
				
				// Check for the presence of opposite action
				rule = new Rule(oppositeAction, actuator);
				
				if(oppositeAction != null) {
					if(this.map.containsKey(rule)) {
						this.check(thresholdValue, max, oppositeAction, ruleId, sensorId);	
					} else {
						this.add(oppositeAction, actuator, thresholdValue, ">=", ruleId, sensorId);
					}
				} 
				break;
			case "<=":
				it.put(min, thresholdValue, ruleId, sensorId);
				this.map.put(rule, it);
				
				break;
			case ">":
				it.put(thresholdValue + 1, max, ruleId, sensorId);
				this.map.put(rule, it);
				break;
			case ">=":
				it.put(thresholdValue, max, ruleId, sensorId);
				this.map.put(rule, it);
				break;
			case "=":
				break;
			case "!=":
				break;
			}
		} catch (RuleWarnings r) {
			this.warningList.add("WARNING: " + sensorId + " in conflict with" + r.getMessage());
		}
	}

	private void check(int min, int max, String action, String ruleId, String sensorId) {
		/*try {
			
		} catch (RuleConflictException e) {
			this.conflictList.add(ruleId + " in conflict with " + e.getMessage());
		}*/
	}
	
	private String getOppositeAction(String action) {
		String oppositeAction = null;
		switch(action) {
		case "turn on" : 
			oppositeAction = "turn off";
			break;
		case "turn off":
			oppositeAction = "turn on";
			break;
		}
		return oppositeAction;
	}
	
	public static void main(String[] args) {
		Connection connection = null;
		Test t = new Test();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquafonics", "root", "welcome");

			String query = 
			"SELECT AC.Action_Name, AC.Action_Parameters, ST.Threshold_Value, OP.Operator_Name, ST.Rule_RID, ST.Sensor_ID"
			+ " FROM actions AS AC" 
			+ " JOIN STATEMENTS AS ST ON ST.rule_RID = AC.rule_RID" 
			+ " JOIN OPERATORS AS OP ON OP.oid = ST.oid"
			+ " WHERE AC.ATID in (5, 6);";
			
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				t.add(rs.getString("Action_Name"), rs.getString("Action_Parameters"), 
						Integer.parseInt(rs.getString("threshold_value")), rs.getString("operator_name"),
						rs.getString("rule_rid"), rs.getString("sensor_id"));
			}
			
			// Print conflicts 
			if(t.conflictList.isEmpty()) {
				System.out.println("Rules have no conflict.");
			} else {
				System.out.println("Rule conflict is present in the following.");
				for(String item : t.conflictList) {
					System.out.println(item);
				}
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
