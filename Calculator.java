import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double num1, num2, result;
        String operator, moveOn = "continue";
        int num1ExceptionState = 0, num2ExceptionState = 0;

        while (moveOn.equals("continue")) {
            System.out.println("Enter the first number");

            do {
                try {
                    System.out.println("Get the number input...");
                    num1ExceptionState = 0;
                    num1 = scan.nextFloat();
                    scan.nextLine();
                } catch(InputMismatchException exception) {
                    System.out.println("The number caught an exception");
                    num1ExceptionState ++;
                }
            } while(num1ExceptionState == 1);

            /* 
            scan.nextLine();

            System.out.println("Enter the second number");
            num2 = scan.nextFloat();
            scan.nextLine();

            System.out.println("Enter the operation");
            operator = scan.nextLine();

            result = getResult(num1, num2, operator);
            printResults(num1, num2, operator, result);
            */

            System.out.println("Type \"continue\" to use the calculator one more time and anything else to stop");
            moveOn = scan.nextLine();
        }
    }

    static void printResults(double num1, double num2, String operator, double result) {
        System.out.println("" + num1 + " " + operator + " " + num2 + " is: " + result);
    }

    static double getResult(double num1, double num2, String operator) {
        if (operator.equals("*")) {
            return num1 * num2;
        } else if (operator.equals("/")) {
            return num1 / num2;
        } else if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else {
            return 0;
        }
    }
}