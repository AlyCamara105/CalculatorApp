import java.util.Scanner;

public class Calculator {
    
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        float num1, num2, result;
        String operand;


        System.out.println("Enter the first number");
        num1 = scan.nextFloat();

        System.out.println("Enter the second number");
        num2 = scan.nextFloat();

        scan.nextLine();

        System.out.println("Enter the operation");
        operand = scan.nextLine();

        if (operand.equals("*")) {
            result = num1 * num2;
            System.out.println("The answer is: " + result);
        } else if (operand.equals("/")) {
            result = num1 / num2;
            System.out.println("The answer is: " + result);
        } else if (operand.equals("+")) {
            result = num1 + num2;
            System.out.println("The answer is: " + result);
        } else if (operand.equals("-")) {
            result = num1 - num2;
            System.out.println("The answer is: " + result);
        }
    }
}