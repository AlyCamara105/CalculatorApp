import java.util.Scanner;

public class Calculator {
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        float num1, num2, result;
        String operand, moveOn = "continue";

        while (moveOn.equals("continue")) {
            System.out.println("Enter the first number");
            num1 = scan.nextFloat();
            scan.nextLine();

            System.out.println("Enter the second number");
            num2 = scan.nextFloat();
            scan.nextLine();

            System.out.println("Enter the operation");
            operand = scan.nextLine();

            if (operand.equals("*")) {
                result = num1 * num2;
                printResults(num1, num2, operand, result);
            } else if (operand.equals("/")) {
                result = num1 / num2;
                printResults(num1, num2, operand, result);
            } else if (operand.equals("+")) {
                result = num1 + num2;
                printResults(num1, num2, operand, result);
            } else if (operand.equals("-")) {
                result = num1 - num2;
                printResults(num1, num2, operand, result);
            }
            System.out.println("Type \"continue\" to use the calculator one more time and anything else to stop");
            moveOn = scan.nextLine();
        }
    }

    static void printResults(float num1, float num2, String operand, float result) {
        System.out.println("" + num1 + " " + operand + " " + num2 + " is: " + result);
    }
}