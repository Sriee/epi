package com.rule;

import java.util.Iterator;
import java.util.List;

import com.exceptions.RuleConflict;
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
		
		for (int i = 0; i < 2; i++) {
			jsonExpression = "boolean_expression" + (i + 1) + ".json";
			log.writeLog("Working on " + jsonExpression);
			
			List<Container> containerList = builder.build(jsonExpression);
			log.writeLog("Container List size: " + containerList.size());
			ContainerIterator ci = new ContainerIterator();

			// Should check for rule conflict here
			for (Container lhs : containerList) {
				try {
					
					// If rule table is empty => First rule
					if (builder.isRuleTableEmpty()) {
						log.writeLog("Rule Table is empty. Adding first entry.");
						builder.add(lhs);
						continue;
					}
					
					// Iterate through rest of the rule for detecting conflict
					Iterator<Container> iter = ci.iterator();
					while (iter.hasNext()) {
						Container rhs = iter.next();
						log.writeLog(String.format("%s vs %s", lhs.getRuleName(), rhs.getRuleName()));
						lhs.checkConflict(rhs);
					}

					log.writeLog("Rule: " + lhs.getExpression() + " added sucessfully.");
					builder.add(lhs);

				} catch (RuleConflict e) {
					log.writeLog("[ERROR] Rule Conflict detected in " + lhs.getExpression());
					e.printStackTrace();
				}
			}
		}
		
		factory.close();
		log.writeLog("Done!");
	}
}
