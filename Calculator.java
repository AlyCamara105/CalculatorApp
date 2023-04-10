import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static final String sentinel = "stop";
    static boolean clear = false, history = false;
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double num1, num2;
        String operator;

        System.out.println("Instructions:\nInput: \"stop\" to exit the calculator and \"clear\" to clear all previous inputs.\nUse the following operators to use the calculator: *, /, +, -, ^, %, log.\nFor exponents the first number is the exponent base and the second number is the exponent.\nFor logarithms the first number is the number inputed in the logarithm and the second number is the log base.\nUse \"pi\" for pi and \"e\" for the e.");

        while(true) {

            System.out.println("\n");
            num1 = getDouble("Enter the first number", scan);
            num2 = getDouble("Enter the second number", scan);
            operator = getOperator("Enter the operation", scan);
            printResults(num1, num2, operator, getResult(num1, num2, operator));
            if(clear) {
                clear = false;
            }
        }
    }

    static void printResults(double num1, double num2, String operator, double result) {
        if(!clear) {
            if(operator.equals("log")) {
                System.out.println(operator + " base " + num2 + " (" + num1 + ") is: " + result);
            } else {
                System.out.println("" + num1 + " " + operator + " " + num2 + " is: " + result);
            }
        }
    }

    static double getResult(double num1, double num2, String operator) {
        if(!clear) {
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
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    static double getDouble(String prompt, Scanner scan) {
        boolean excepted = false;
        double num = 0.0;

        do {
            try {
                if(!clear) {
                    excepted = false;
                    System.out.println(prompt);
                    num = scan.nextDouble();
                    scan.nextLine();
                }
            } catch(InputMismatchException exception) {
                String input = scan.nextLine().toLowerCase();
                checkToTerminate(input);
                num = getMathSymbolValue(input);
                if(checkToClear(input)) {
                    clear = true;
                } else if(num == 0.0) {
                    System.out.println("\nInput Invalid\n");
                    excepted = true;
                }
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

        while(!operatorIsValid && !clear) {
            System.out.println(prompt);
            operator = scan.nextLine().toLowerCase();
            checkToTerminate(operator);
            for(int i = 0; i < validOperators.length; i++) {
                if(operator.equals(validOperators[i])) {
                    operatorIsValid = true;
                }
            }
            if(checkToClear(operator)) {
                clear = true;
            } else if(!operatorIsValid) {
                System.out.println("\nInvalid Input\n");
            }
        }

        return operator;
    }

    static double getMathSymbolValue(String input) {
        if(input.equals("e")) {
            return Math.E;
        } else if(input.equals("pi")) {
            return Math.PI;
        } else {
            return 0.0;
        }
    }

    static boolean checkToClear(String input) {
        return input.equals("clear");
    }
}