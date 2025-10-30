import java.util.Scanner;

public class quetion1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        System.out.println("Sum: " + (num1 + num2));
        System.out.println("Difference: " + (num1 - num2));
        System.out.println("Product: " + (num1 * num2));

        if (num2 != 0) {
            System.out.println("Quotient: " + (num1 / num2));
            System.out.println("Remainder: " + (num1 % num2));
        } else {
            System.out.println("Quotient: undefined");
            System.out.println("Remainder: undefined");
        }

        sc.close();
    }
}