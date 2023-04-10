import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    static final String sentinel = "stop";
    static boolean clear = false, showHistory = false, expressionMode = false;
    static String historyDefault = "\nHistory:\n", history = "\nHistory:\n", operator, function;
    static final Scanner scan = new Scanner(System.in);
    static double num1, num2;
    
    public static void main(String [] args) {
        System.out.println("Instructions:\nInput: \"stop\" to exit the calculator, \"clear\" to clear all previous inputs, or \"history\" to show previous calculations.\nUse the following operators or functions to use the calculator: *, /, +, -, ^, %, log, sin, arcsin, cos, arcos, tan, arctan, abs, !.\nFor exponents the first number is the exponent base and the second number is the exponent.\nFor logarithms the first number is the number inputed in the logarithm and the second number is the logarithm base.\nUse \"pi\" for PI and \"e\" for the mathematical constant.");

        while(true) {

            System.out.println("\n");
            queryCalculatorUse();
            printResults();
            if(clear) {
                clear = false;
            }
            if(showHistory) {
                if(history.equals(historyDefault)) {
                    System.out.println("\nNo History");
                } else {
                    System.out.println(history);
                }
                showHistory = false;
            }
        }
    }

    static void printResults() {
        if(!clear) {
            String output = "";
            if(!expressionMode) {
                double result = getOperatorResult();
                if(operator.equals("log")) {
                    output = operator + " base " + num2 + " (" + num1 + ") is: " + result;
                } else {
                    output = "" + num1 + " " + operator + " " + num2 + " is: " + result;
                }
            } else {
                double result = getFunctionResult();
                if(function.equals("!")) {
                    output = "" + num1 + function + " is: " + result;
                } else {
                    output = function + "(" + num1 + ") is: " + result;
                }
            }
            history += output + "\n";
            System.out.println("\n" + output);
        }
    }

    static double getOperatorResult() {
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

    static double getDouble(String prompt) {
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

    static void getOperator() {
        boolean operatorIsValid = false;
        String[] validOperators = {"*", "/", "+", "-", "^", "%", "log"};

        while(!operatorIsValid && !clear) {
            System.out.println("Input an operation");
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
        boolean internalClear = false;
        if(input.equals("history")) {
            internalClear = true;
            showHistory = true;
        } else if(input.equals("clear")) {
            internalClear = true;
        }
        return internalClear;
    }

    static void useOperator() {
        num1 = getDouble("Input the first number");
        num2 = getDouble("Input the second number");
        getOperator();
    }

    static void queryCalculatorUse() {
        String input = "";
        boolean inputIsValid = false;
        String[] validInputs = {"expression", "operator"};

        while(!inputIsValid) {
            System.out.println("Input \"expression\" to evaluate an expression or \"operator\" to use the operators");
            input = scan.nextLine().toLowerCase();
            checkToTerminate(input);
            if(checkToClear(input)) {
                clear = true;
                inputIsValid = true;
            }
            if(!clear) {
                for(int i = 0; i < validInputs.length; i++) {
                    if(input.equals(validInputs[i])) {
                        inputIsValid = true;
                    }
                }
                if(!inputIsValid) {
                    System.out.println("\nInvalid Input\n");
                }
            }
        }
        if(!clear) {
            if(input.equals("expression")) {
                expressionMode = true;
                useExpression();
            } else {
                expressionMode = false;
                useOperator();
            }
        }
    }

    static void useExpression() {
        getFunction();
        num1 = getDouble("Input the number");
    }

    static void getFunction() {
        String[] validFunctions = {"sin", "cos", "tan", "arcsin", "arccos", "arctan", "abs", "!"};
        boolean functionIsValid = false;

        while(!functionIsValid) {
            System.out.println("Input a function");
            function = scan.nextLine().toLowerCase();
            checkToTerminate(function);
            if(checkToClear(function)) {
                clear = true;
                functionIsValid = true;
            }
            if(!clear) {
                for(int i = 0; i < validFunctions.length; i++) {
                    if(function.equals(validFunctions[i])) {
                        functionIsValid = true;
                    }
                }
                if(!functionIsValid) {
                    System.out.println("\nInvalid Input\n");
                }
            }
        }
    }

    static double getFunctionResult() {
        if(function.equals("sin")) {
            return Math.sin(num1);
        } else if(function.equals("cos")) {
            return Math.cos(num1);
        } else if(function.equals("tan")) {
            return Math.tan(num1);
        } else if(function.equals("arcsin")) {
            return Math.asin(num1);
        } else if(function.equals("arccos")) {
            return Math.acos(num1);
        } else if(function.equals("atan")) {
            return Math.atan(num1);
        } else if(function.equals("abs")) {
            return Math.abs(num1);
        } else if(function.equals("!")) {
            double result = num1;
            for(double i = result; i > 1; i--) {
                result *= (i - 1);
            }
            return result;
        } else {
            return 0.0;
        }
    }
}