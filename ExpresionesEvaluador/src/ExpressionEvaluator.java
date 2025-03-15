	import java.util.*;

	public class ExpressionEvaluator {
	    
	   
	    public static boolean areParenthesesBalanced(String expression) {
	        Stack<Character> stack = new Stack<>();
	        for (char ch : expression.toCharArray()) {
	            if (ch == '(') {
	                stack.push(ch);
	            } else if (ch == ')') {
	                if (stack.isEmpty()) {
	                    return false;
	                }
	                stack.pop();
	            }
	        }
	        return stack.isEmpty();
	    }

	  
	    public static String infixToPostfix(String expression) {
	        StringBuilder output = new StringBuilder();
	        Stack<Character> stack = new Stack<>();
	        Map<Character, Integer> precedence = new HashMap<>();
	        precedence.put('+', 1);
	        precedence.put('-', 1);
	        precedence.put('*', 2);
	        precedence.put('/', 2);

	        for (char ch : expression.toCharArray()) {
	            if (Character.isDigit(ch)) {
	                output.append(ch);
	            } else if (ch == '(') {
	                stack.push(ch);
	            } else if (ch == ')') {
	                while (!stack.isEmpty() && stack.peek() != '(') {
	                    output.append(stack.pop());
	                }
	                stack.pop(); // Eliminar el '('
	            } else {
	                while (!stack.isEmpty() && precedence.getOrDefault(stack.peek(), 0) >= precedence.get(ch)) {
	                    output.append(stack.pop());
	                }
	                stack.push(ch);
	            }
	        }
	        
	        while (!stack.isEmpty()) {
	            output.append(stack.pop());
	        }
	        
	        return output.toString();
	    }

	    
	    public static int evaluatePostfix(String expression) {
	        Stack<Integer> stack = new Stack<>();
	        for (char ch : expression.toCharArray()) {
	            if (Character.isDigit(ch)) {
	                stack.push(ch - '0');
	            } else {
	                int b = stack.pop();
	                int a = stack.pop();
	                switch (ch) {
	                    case '+': stack.push(a + b); break;
	                    case '-': stack.push(a - b); break;
	                    case '*': stack.push(a * b); break;
	                    case '/': stack.push(a / b); break;
	                }
	            }
	        }
	        return stack.pop();
	    }
	    
	    
	    public static void main(String[] args) {
	        String infixExpression = "(3+5)*2";
	        
	        if (!areParenthesesBalanced(infixExpression)) {
	            System.out.println("Error: Los paréntesis no están balanceados.");
	            return;
	        }
	        
	        String postfixExpression = infixToPostfix(infixExpression);
	        System.out.println("Expresión postfija: " + postfixExpression);
	        
	        int result = evaluatePostfix(postfixExpression);
	        System.out.println("Resultado de la evaluación: " + result);
	    }
	}


