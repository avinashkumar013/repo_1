// Check Number Parity
// Write a program in java to check whether the given number is an even number or not using if else statement.
// Sample Test Case 1
// input
// 12
// output
// No is Even
// Sample Test Case 2
// input
// 13
// output
// No is odd
package Day1;

import java.util.*;

public class CheckNumber {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        System.out.println(num % 2 == 0 ? "No is Even" : "No is odd");

    }
}
