import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Word Counter!");

        // Step 1: Prompt the user to enter a text or provide a file
        System.out.println("Enter 'text' to input text, or 'file' to input a file:");
        String inputMethod = scanner.nextLine().toLowerCase();

        String text = "";
        // Step 2: Read the input text or file and store it in a string
        if (inputMethod.equals("text")) {
            System.out.println("Enter your text:");
            text = scanner.nextLine();
        } else if (inputMethod.equals("file")) {
            System.out.println("Enter the path to your file:");
            String filePath = scanner.nextLine();
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + " ";
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                return;
            }
        } else {
            System.out.println("Invalid input method. Please enter 'text' or 'file'.");
            return;
        }

        // Step 3: Split the string into an array of words
        String[] words = text.split("\\s+|\\p{Punct}");

        // Step 4: Initialize a counter variable
        int wordCount = 0;

        // Step 7: Ignoring common words or stop words
        Set<String> stopWords = new HashSet<>();
        stopWords.add("the");
        stopWords.add("and");
        // Add more stop words as needed
        
        // Step 8: Providing statistics like the number of unique words or the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();
        
        // Step 5: Iterate through the array of words and count
        for (String word : words) {
            if (!word.isEmpty()) {
                word = word.toLowerCase(); // Normalize the word
                // Step 7: Ignoring common words or stop words
                if (!stopWords.contains(word)) {
                    wordCount++;
                    // Step 8: Providing statistics like the number of unique words or the frequency of each word
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Step 6: Display the total count of words
        System.out.println("Total words: " + wordCount);

        // Step 8: Providing statistics like the number of unique words or the frequency of each word
        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Step 9: Implementing input validation to handle empty inputs or file errors
        if (wordCount == 0) {
            System.out.println("No words found in the input.");
        }

        // Step 10: Adding a graphical user interface (GUI) for a more user-friendly experience
        // Not implemented in this console-based version
    }
}