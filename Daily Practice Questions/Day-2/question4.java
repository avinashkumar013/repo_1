// You are given a string s. In one move you can change any character to another character.
// You have to make a string which consists of the same character. Find the minimum moves to do this task.
// Sample Input:
// aabbbcccc
// Sample Output:
// 5
// Explanation:
// Since character 'c' has the maximum frequency (4 times), make all other characters 'c':
// Convert 2 'a' and 3 'b' into 'c', total 5 moves.

// Java answer (solution):
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        int maxFreq = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
            }
        }
        int minMoves = s.length() - maxFreq;
        System.out.print(minMoves);
    }
}
