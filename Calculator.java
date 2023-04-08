import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        double num1, num2, result;
        String operator, moveOn = "continue";

        while (moveOn.equals("continue")) {

            num1 = getFloat("Enter the first number", scan);
            num2 = getFloat("Enter the second number", scan);

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

    static float getFloat(String prompt, Scanner scan) {
        boolean excepted;
        float num;

        do {
            try {
                System.out.println(prompt);
                excepted = false;
                num = scan.nextFloat();
            } catch(InputMismatchException exception) {
                System.out.println("Input Invalid");
                excepted = true;
                num = 0;
            } finally {
                scan.nextLine();
            }
        } while(excepted);
        
        return num;
    }
}