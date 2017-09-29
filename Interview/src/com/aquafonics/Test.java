package com.aquafonics;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Test {
	private HashMap<Rule, IntervalTree> map = null;
	private LinkedList<String> conflictList = null;
	
	public Test() {
		this.map = new HashMap<>();
		this.conflictList = new LinkedList<>();
	}

	private void add(int thresholdValue, String operator, String sensorId, String actuatorId, String ruleId) {
		int min = 0, max = 150;
		Rule rule = null;
		IntervalTree it = null;
		try {
				rule = new Rule(sensorId, actuatorId);
				it = (this.map.containsKey(rule)) ? this.map.get(rule) : new IntervalTree();
			switch (operator) {
			case "<":
				it.put(min, thresholdValue - 1, ruleId);
				this.map.put(rule, it);
				break;
			case "<=":
				it.put(min, thresholdValue, ruleId);
				this.map.put(rule, it);
				break;
			case ">":
				it.put(thresholdValue + 1, max, ruleId);
				this.map.put(rule, it);
				break;
			case ">=":
				it.put(thresholdValue, max, ruleId);
				this.map.put(rule, it);
				break;
			case "=":
				break;
			case "!=":
				break;
			}
		} catch (RuleConflictException r) {
			this.conflictList.add(ruleId + " in conflict with " + r.getMessage());
		}
	}

	public static void main(String[] args) {
		Connection connection = null;
		Test t = new Test();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aquafonics", "root", "welcome");
			String query = "SELECT ST.Threshold_Value, OP.Operator_Name, ST.Rule_RID, ST.Sensor_ID "
					+ "FROM STATEMENTS AS ST, OPERATORS AS OP " + "WHERE ST.OID = OP.OID;";
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				t.add(Integer.parseInt(rs.getString("threshold_value")), rs.getString("operator_name"), 
						rs.getString("sensor_id"), null, rs.getString("rule_rid"));
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
