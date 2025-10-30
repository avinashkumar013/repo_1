// Write a function to reverse each word in a string.
// Description:
// A method has to be created that takes a string as input, extracts each word from that string, then reverses each word individually and gives the output as a reversed string.
// Example:
// Input: great learning
// Output: taerg gninrael
// Input: hello guys how are you
// Output: olleh syug woh era uoy

// Solution:
public class Main {
    public static void main(String[] args) {
        String input = "Great Learning"; // manual input

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            result.append(new StringBuilder(words[i]).reverse());
            if (i < words.length - 1) result.append(" ");
        }

        System.out.print(result); // Output: taerG gninraeL
    }
}