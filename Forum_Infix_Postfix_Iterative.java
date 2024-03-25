import java.util.*;

public class Forum_Infix_Postfix_Iterative {
    //Detects operators in the statement
    private static int precedence(char operator) {
        if(operator == '+' || operator == '-'){
            return 1;
        } else if(operator == '*' || operator == '/'){
            return 2;
        } else if(operator == '^'){
            return 3;
        } else{
            return -1;
        }
    }

    //Converts infix to postfix using the method and a for loop
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }else if (c == '('){
                stack.push(c);
            }else if (c == ')'){
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            }else{
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }
    
    //Runs the mehtods
    public static void main(String[] args) {
        String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("Infix Expression: " + infixExpression);

        long time_start = System.nanoTime();

        String postfixExpression = infixToPostfix(infixExpression);

        long time_end = System.nanoTime();

        double time_taken_ms = (double) (time_end - time_start) / 1_000_000.0;

        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Time taken: " + time_taken_ms + " ms");
    }
}

