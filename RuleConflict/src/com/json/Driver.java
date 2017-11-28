package com.json;

import java.util.List;
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
import com.logger.FileLogger;

public class Driver {

    public static void main(String[] args) throws IOException {
        // Configure gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Input.class, new InputDeserializer());
        Gson gson = gsonBuilder.create();

        FileLogger log = FileLogger.instance();
        Parser p = new Parser();

        JsonReader reader = null;
        List<String> ruleTokens = null;
        try {
            reader = new JsonReader(new FileReader(Paths.get(Paths.get(".").toAbsolutePath().toString(),
                    "resources/boolean_expression.json").normalize().toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Input sample = gson.fromJson(reader, Input.class);
        log.writeLog(sample.toString());

        try {
            TreeNode root = p.buildAST(sample.getConditionalExpression());

            p.inOrder(root);
            System.out.println();

            Expression<String> expression = p.buildExpression(root);
            System.out.println("Expression: " + expression);
            Expression<String> sop = RuleSet.toSop(expression);
            String dnf = sop.toString();
            System.out.println("DNF: " + dnf);

            dnf = p.stripBrackets(dnf);
            ruleTokens = p.rulesAsTokens(dnf);

            for(String item : ruleTokens)
                System.out.println(item);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
