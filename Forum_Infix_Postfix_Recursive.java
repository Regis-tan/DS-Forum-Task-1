import java.util.Stack;


public class Forum_Infix_Postfix_Recursive {
    //Detects operators in the statement
    private static int precedence(char operator) {
        //If the operator is add or subtract it returns 1
        if (operator == '+' || operator == '-') {
            return 1;
        //If the operator is multiply or divide it returns 2
        } else if (operator == '*' || operator == '/') {
            return 2;
        //If the operator is power it returns 3
        } else if (operator == '^') {
            return 3;
        //If it does not detect an operator it returns -1
        } else {
            return -1;
        }
    }

    //Creates method "infixtoPostfixRecursive"
    private static void infixToPostfixRecursive(String infix, int index, Stack<Character> stack, StringBuilder postfix) {
        //If the index is the same as the length of the infix expression it runs the while loop
        if (index == infix.length()) {
            //While the stack is not empty and the top of the stack does not contain an opening bracket it will keep appending into the postfix string builder and pop from the stack.
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop());
            }
            return;
        }
        //Changes the character c to the character in index in infix
        char c = infix.charAt(index);
        //If c is a letter or digit it will append to the postfix string builder
        if (Character.isLetterOrDigit(c)) {
            postfix.append(c);
        //If c is an opening bracket it will push c in the stack
        } else if (c == '(') {
            stack.push(c);
        //If c is a closing bracket it will run the while loop
        } else if (c == ')') {
            //While the stack is not empty and the top of the stack not opening bracket it will keep appending to the postfix string builder and popping the stack.
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop());
            }
            //Pops the stack
            stack.pop();
        //If c is not a letter, digit, opening bracket or closing bracket it will run this while loop.
        } else {
            //Runs the while loop
            while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                postfix.append(stack.pop());
            }
            //Pushes c in the stack
            stack.push(c);
        }
        //Calls the infixtopostfixrecursive method and adds the index by 1
        infixToPostfixRecursive(infix, index + 1, stack, postfix);
    }

    //Creates method infixToPostfix which uses the infixToPostfixRecursive method
    public static String infixToPostfix(String infix) {
        //Creates a stack and the postfix string builder
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        //Calls the infixtopostfixrecursive method
        infixToPostfixRecursive(infix, 0, stack, postfix);
        //If the stack is not empty it will keep appending to postfix and popping the stack
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        //Returns the string version of the postfix
        return postfix.toString();
    }

    //Runs the methods
    public static void main(String[] args) {
        //Defines the infix version of the expression
        String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
        //Prints the infix expression
        System.out.println("Infix Expression: " + infixExpression);
        //Records the starting time
        long time_start = System.nanoTime();
        //Converts the infix to postfix expression
        String postfixExpression = infixToPostfix(infixExpression);
        //Records the end time
        long time_end = System.nanoTime();
        //Calculates the time taken in milliseconds
        double time_taken_ms = (double) (time_end - time_start) / 1_000_000.0;

        //Prints the postfix version of the expression and the time taken
        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Time taken: " + time_taken_ms + " ms");
    }
}
