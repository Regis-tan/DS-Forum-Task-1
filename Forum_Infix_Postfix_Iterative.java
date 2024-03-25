import java.util.*;

public class Forum_Infix_Postfix_Iterative {
    //Detects operators in the statement
    private static int precedence(char operator) {
        //If the operator is add or subtract it returns 1
        if(operator == '+' || operator == '-'){
            return 1;
        //If the operator is multiply or divide it returns 2
        } else if(operator == '*' || operator == '/'){
            return 2;
        //If the operator is power it returns 3
        } else if(operator == '^'){
            return 3;
        //If it does not detect an operator, it returns -1
        } else{
            return -1;
        }
    }

    //Converts infix to postfix using the method and a for loop
    public static String infixToPostfix(String infix) {
        //Creates a stack and string builder
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        //It turns the expression into an array with each character inside
        for (char c : infix.toCharArray()) {
            //If c is a letter or digit it appends c into the postfix stringbuilder
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            //If c is an opening bracket it will push c into the stack
            }else if (c == '('){
                stack.push(c);
            //If c is a closing bracket it will execute the while loop
            }else if (c == ')'){
                //The while loop will keep appending to postfix and popping the current element until either the stack is empty or the first element in the stack is an opening bracket.
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                //Pops to top element in the stack
                stack.pop();
            //If c does not fall into the other categories it will run this while loop
            }else{
                //This while loop will keep appending the first element in the stack into the postfix stringbuilder
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                //Pushes c in the stack
                stack.push(c);
            }
        }
        //Runs a while loop which appends to postfix and pops the stack until it is empty
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        //Coverts postfix into string
        return postfix.toString();
    }
    
    //Runs the mehtods
    public static void main(String[] args) {
        //This is the input
        String infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
        //Prints the infix form of the expressions
        System.out.println("Infix Expression: " + infixExpression);

        //Records the start of the operation
        long time_start = System.nanoTime();

        //Executes the code
        String postfixExpression = infixToPostfix(infixExpression);

        //Records the end of the operation
        long time_end = System.nanoTime();

        //Calculates the time taken
        double time_taken_ms = (double) (time_end - time_start) / 1_000_000.0;

        //Prints the postfix version of the expression and prints the time taken
        System.out.println("Postfix Expression: " + postfixExpression);
        System.out.println("Time taken: " + time_taken_ms + " ms");
    }
}

