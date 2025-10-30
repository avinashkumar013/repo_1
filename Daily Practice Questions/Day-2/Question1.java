import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int size = inp.nextInt(); 
        int[] arr = new int[size];

        // read the array elements
        for (int i = 0; i < size; i++) {
            arr[i] = inp.nextInt();
        }

        // Calculate sum of elements
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        // Print the result in the required format
        System.out.println("the sum of the elements in the array = " + sum);
    }
}
