package com.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Map;
import java.util.HashMap;

/**
 * Desrializer for parsing Json into Input type
 * @author sriee
 *
 */
public class InputDeserializer implements JsonDeserializer<Input> {

	/**
	 * Implementation of gson method
	 * 
	 * @param JsonElement json
	 * @param Type type
	 * @param JsonDeserializationContext context
	 * @throws JsonParseException
	 */
	@Override
	public Input deserialize(final JsonElement json, final Type type, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		JsonObject literalObject = null;
		String temp = null;
		Input input = new Input();
		Literals literalInstance = null;
		Map<String, Literals> map = new HashMap<>();
		LogicalOperator logicOp = LogicalOperator.EQUAL; 
		
		// Condition expression mapping
		input.setConditionalExpression(jsonObject.get("condition_expression").getAsString());
		
		// Action expression mapping
		input.setActionExpression(jsonObject.get("action_expression").getAsString());
		
		// Condition count mapping
		input.setConditionCount(jsonObject.get("condition_count").getAsInt());
		
		// Action count mapping
		input.setActionCount(jsonObject.get("action_count").getAsInt());
		
		// Store conditions in map 
		for(int i = 1; i <= input.getConditionCount(); i++){
			temp = "c" + i;
			literalObject = jsonObject.get(temp).getAsJsonObject();
			literalInstance = new Literals(literalObject.get("name").getAsString(),
					literalObject.get("id").getAsString(),
					(LogicalOperator) logicOp.getOperator(literalObject.get("operator").getAsString()),
					literalObject.get("value").getAsInt()
					);
			map.put(temp, literalInstance);
		}
		
		// Store actions in Map
		for(int j = 1; j <= input.getActionCount(); j++){
			temp = "a" + j;
			literalObject = jsonObject.get(temp).getAsJsonObject();
			literalInstance = new Literals(literalObject.get("name").getAsString(),
					literalObject.get("id").getAsString(),
					(LogicalOperator) logicOp.getOperator(literalObject.get("operator").getAsString()),
					literalObject.get("value").getAsInt()
					);
			map.put(temp, literalInstance);
		}
		 
		input.setLiterals(map); // Set literals with map of condition and action
		return input;
	}

}
