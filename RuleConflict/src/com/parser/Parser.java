package com.parser;

import java.util.Stack;

import com.exceptions.InvalidExpressionSyntaxException;

public class Parser {

	public TreeNode checkSyntax(String expression) throws InvalidExpressionSyntaxException {
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

	private String formExpression(String expression) throws InvalidExpressionSyntaxException {
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

	public void inOrder(TreeNode root) {
		if (root == null)
			return;

		this.inOrder(root.left);
		System.out.print(root.name + " ");
		this.inOrder(root.right);
	}
}
