import java.util.Scanner;

public class question4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();  // input number

        if(num % 2 == 0) {
            System.out.println("No is Even");
        } else {
            System.out.println("No is odd");
        }

        sc.close();
    }
}