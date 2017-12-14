package com.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.bpodgursky.jbool_expressions.And;
import com.bpodgursky.jbool_expressions.Expression;
import com.bpodgursky.jbool_expressions.Or;
import com.bpodgursky.jbool_expressions.Variable;
import com.exceptions.InvalidExpressionSyntaxException;

/**
 * Parser performs the following two functionality
 * 
 * 	1. Convert String Expression into Abstract Syntax Tree
 * 	2. Builds the expression tree according to jbool expression
 *   
 * @author sriee
 */
public class Parser {

	/**
	 * Constructs abstract syntax tree for a string based boolean expression
	 * 
	 * @param expression Boolean expression
	 * @return Root node of abstract syntax tree
	 * @throws InvalidExpressionSyntaxException
	 */
	public TreeNode buildAST(String expression) throws InvalidExpressionSyntaxException {
		if (expression == null)
			throw new NullPointerException("Expression is null");

		expression = expression.trim();
		expression = this.formExpression(expression);
		int i = -1;

		Stack<Object> valueStack = new Stack<>();
		Stack<String> operatorStack = new Stack<>();

		String tokens[] = expression.split(" ");
		try {
			for (i = 0; i < tokens.length; i++) {
				if (tokens[i] == " ")
					continue;
				if (tokens[i].equals("(") || tokens[i].equals("&") || tokens[i].equals("|")) {
					operatorStack.push(tokens[i]);
				} else if (tokens[i].equals(")")) {
					if (operatorStack.isEmpty())
						throw new InvalidExpressionSyntaxException("Paranthesis mismatch at pos(" + i + ")");

					while (!operatorStack.peek().equals("(")) {
						TreeNode node = new TreeNode(operatorStack.pop());

						node.right = (valueStack.peek() instanceof TreeNode) ? (TreeNode) valueStack.pop()
								: new TreeNode((String) valueStack.pop());
						node.left = (valueStack.peek() instanceof TreeNode) ? (TreeNode) valueStack.pop()
								: new TreeNode((String) valueStack.pop());

						valueStack.push(node);
					}
					operatorStack.pop();
				} else {
					valueStack.push(tokens[i]);
				}
			}
			
			while (!operatorStack.isEmpty()) {
				TreeNode node = new TreeNode(operatorStack.pop());

				node.right = (valueStack.peek() instanceof TreeNode) ? (TreeNode) valueStack.pop()
						: new TreeNode((String) valueStack.pop());
				node.left = (valueStack.peek() instanceof TreeNode) ? (TreeNode) valueStack.pop()
						: new TreeNode((String) valueStack.pop());

				valueStack.push(node);
			}

		} catch (Exception e) {
			throw new InvalidExpressionSyntaxException("Parsing error at pos(" + i + ")");
		}
		
		if (valueStack.isEmpty())
			throw new InvalidExpressionSyntaxException("Root node not found.");

		if (!(valueStack.peek() instanceof TreeNode))
			throw new InvalidExpressionSyntaxException("Parsing failed.");
		
		return (TreeNode) valueStack.pop();
	}

	/**
	 * Formats the boolean expression correctly to be split into tokens
	 * 
	 * @param expression	Boolean expression
	 * @return	Formatted boolean expression
	 * @throws InvalidExpressionSyntaxException
	 */
	public String formExpression(String expression) throws InvalidExpressionSyntaxException {
		StringBuilder sb = new StringBuilder();
		boolean delimiter = true;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == ' ') {
				if (!delimiter) {
					sb.append(" ");
					delimiter = true;
				}
				continue;
			} else if ((expression.charAt(i) - '0' >= 'a' - '0' && expression.charAt(i) - '0' <= 'z' - '0')
					|| (expression.charAt(i) - '0' >= 'A' - '0' && expression.charAt(i) - '0' <= 'Z' - '0')
					|| (expression.charAt(i) - '0' >= 0 && expression.charAt(i) - '0' <= 9)) {
				sb.append(expression.charAt(i));
				delimiter = false;
			} else if (expression.charAt(i) == '&' || expression.charAt(i) == '|' || expression.charAt(i) == '('
					|| expression.charAt(i) == ')') {

				if (!delimiter) {
					sb.append(" ");
					delimiter = true;
				}
				sb.append(expression.charAt(i));
				sb.append(" ");
			} else {
				throw new InvalidExpressionSyntaxException("Unkown token found at pos(" + i +")");
			}
		}
		return sb.toString();
	}

	/**
	 * Inorder traversal. To check whether the Abstract Syntax Tree is built
	 * properly
	 * 
	 * @param root Root node of Abstract Syntax Tree
	 */
	public void inOrder(TreeNode root) {
		if (root == null)
			return;

		this.inOrder(root.left);
		System.out.print(root.name + " ");
		this.inOrder(root.right);
	}

	/**
	 * Performs inorder reversal and transforms string based Abstract Syntax Tree to jbool
	 * expression
	 * 
	 * @param root Root Node of Abstract Syntax Tree 
	 * @return	jbool type expression
	 */
	public Expression<String> buildExpression(TreeNode root){
		if(root.name.equals("&"))
			return And.of(this.buildExpression(root.left), this.buildExpression(root.right));
		else if(root.name.equals("|"))
			return Or.of(this.buildExpression(root.left), this.buildExpression(root.right));
		else
			return Variable.of(root.name);
	}
	
	/**
	 * Split string expression with respect to the delimiter
	 * 
	 * @param expression Boolean expression
	 * @param delimiter	(&, |)
	 * @return	List of tokens 
	 */
	public List<String> splitTokens(String expression, String delimiter){
	    if(expression == null)
	        return null;

	    expression = expression.trim();
	    if(expression.length() == 0)
	        return null;

	    List<String> tokens = new ArrayList<>();
	    expression = expression + delimiter;
        int startIdx = 0, endIdx = expression.indexOf(delimiter);
        String temp = null;
        while(endIdx != -1){
            temp = expression.substring(startIdx, endIdx);
            startIdx = endIdx + 1;
            temp = this.stripBrackets(temp);
            tokens.add(temp);
            endIdx = expression.indexOf(delimiter, startIdx);
        }
        return tokens;
    }
	
	/**
	 * Utility function to strip brackets of a boolean expression
	 * 
	 * @param expression boolean expression
	 * @return expression with brackets strippped
	 */
	public String stripBrackets(String expression){

	    if(expression == null)
	        return null;

	    expression = expression.trim();
	    if(expression.length() == 0)
	        return expression;

	    if(expression.startsWith("(")){
	        expression = expression.substring(1);
        }

        if(expression.endsWith(")")){
	        expression = expression.substring(0, expression.length() - 1);
        }

        return expression;
    }
}
