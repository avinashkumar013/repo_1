/*
7. Arithmetic Operators in Java
Instructions:
Write a program that takes two numbers as input from the user and performs arithmetic operations on them using the arithmetic operators (sum, difference, product, quotient, remainder) in Java.
Examples:
# Examples: 
1. int num1 = 10; 
   int num2 = 5; 
   int sum = num1 + num2; 
   System.out.println(sum); // Output: 15  
Sample Input & Output:
Sample Input:
Enter the first number: 10
Enter the second number: 5
Sample Output:
Sum: 15
Difference: 5
Product: 50
Quotient: 2
Remainder: 0
Hints:
1. Use the Scanner class to read input from the user.
2. Declare variables to store the input values and the results of the arithmetic operations.
3. Use the appropriate arithmetic operators to perform the calculations.
4. Print the results using the System.out.println() method.
5. Remember to handle division by zero when calculating the quotient.
 */
package Day1;

import java.util.Scanner;

public class ArithmeticOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        int sum = num1 + num2;
        int difference = num1 - num2;
        int product = num1 * num2;

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);

        if (num2 != 0) {
            int quotient = num1 / num2;
            int remainder = num1 % num2;
            System.out.println("Quotient: " + quotient);
            System.out.println("Remainder: " + remainder);
        } else {
            System.out.println("Cannot divide by zero!");
        }

        scanner.close();
    }
}
