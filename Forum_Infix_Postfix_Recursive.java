import java.util.Stack;


public class Forum_Infix_Postfix_Recursive {
    //Detects operators in the statement
    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    //Creates method "infixtoPostfixRecursive"
    private static void infixToPostfixRecursive(String infix, int index, Stack<Character> stack, StringBuilder postfix) {
        if (index == infix.length()) {
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop());
            }
            return;
        }

        char c = infix.charAt(index);
        if (Character.isLetterOrDigit(c)) {
            postfix.append(c);
        } else if (c == '(') {
            stack.push(c);
        } else if (c == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop());
            }
            stack.pop();
        } else {
            while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                postfix.append(stack.pop());
            }
            stack.push(c);
        }

        infixToPostfixRecursive(infix, index + 1, stack, postfix);
    }

    //Creates method infixToPostfix which uses the infixToPostfixRecursive method
    public static String infixToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        infixToPostfixRecursive(infix, 0, stack, postfix);
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    //Runs the methods
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
