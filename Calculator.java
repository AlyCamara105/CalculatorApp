import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double num1, num2, result;
        String operator, moveOn = "continue";
        boolean num1Exception, num2Exception;

        while (moveOn.equals("continue")) {
            do {
                try {
                    System.out.println("Enter the first number");
                    num1Exception = false;
                    num1 = scan.nextFloat();
                } catch(InputMismatchException exception) {
                    System.out.println("Input Invalid");
                    num1Exception = true;
                    num1 = 0;
                } finally {
                    scan.nextLine();
                }
            } while(num1Exception);

            System.out.println("Enter the second number");
            num2 = scan.nextFloat();
            scan.nextLine();

            System.out.println("Enter the operation");
            operator = scan.nextLine();

            result = getResult(num1, num2, operator);
            printResults(num1, num2, operator, result);

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