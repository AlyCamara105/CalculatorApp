import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static String sentinel = "stop";
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double num1, num2, result;
        String operator;

        System.out.println("Instructions: \nInput \"stop\" to exit the calculator. \nUse *, /, +, -, ^, %, log operators to calculate. \nFor logarithms the first number is the number inputed in the logarithm and the second number is the log base.");

        while(true) {

            System.out.println("\n");
            num1 = getFloat("Enter the first number", scan);
            num2 = getFloat("Enter the second number", scan);
            operator = getOperator("Enter the operation", scan);
            result = getResult(num1, num2, operator);
            printResults(num1, num2, operator, result);
        }
    }

    static void printResults(double num1, double num2, String operator, double result) {
        System.out.println("" + num1 + " " + operator + " " + num2 + " is: " + result);
    }

    static double getResult(double num1, double num2, String operator) {
        if(operator.equals("*")) {
            return num1 * num2;
        } else if(operator.equals("/")) {
            return num1 / num2;
        } else if(operator.equals("+")) {
            return num1 + num2;
        } else if(operator.equals("-")) {
            return num1 - num2;
        } else if(operator.equals("^")) {
            return Math.pow(num1, num2);
        } else if(operator.equals("%")) {
            return num1%num2;
        } else if(operator.equals("log")) {
            return Math.log10(num1) / Math.log10(num2);
        } else {
            return 0;
        }
    }

    static float getFloat(String prompt, Scanner scan) {
        boolean excepted;
        float num = 0;

        do {
            try {
                System.out.println(prompt);
                excepted = false;
                num = scan.nextFloat();
                scan.nextLine();
            } catch(InputMismatchException exception) {
                checkToTerminate(scan.nextLine());
                System.out.println("\nInput Invalid\n");
                excepted = true;
            }
        } while(excepted);
        
        return num;
    }

    static void checkToTerminate(String input) {
        if(input.equals(sentinel)) {
            System.exit(0);
        }
    }

    static String getOperator(String prompt, Scanner scan) {
        boolean operatorIsValid = false;
        String[] validOperators = {"*", "/", "+", "-", "^", "%", "log"};
        String operator = "";

        while(!operatorIsValid) {
            System.out.println(prompt);
            operator = scan.nextLine();
            checkToTerminate(operator);
            for(int i = 0; i < validOperators.length; i++) {
                if(operator.equals(validOperators[i])) {
                    operatorIsValid = true;
                }
            }
            if(!operatorIsValid) {
                System.out.println("\nInvalid Input\n");
            }
        }

        return operator;
    }
}