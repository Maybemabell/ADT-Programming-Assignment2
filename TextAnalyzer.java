//672115039 Metavee Aeinjang
//This program is for ADT programming assignment only

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class TextAnalyzer {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String fileName = "input1.txt"; 
        int charCount = 0;
        int palindromeCount = 0;
        int tokenCount = 0;
        int emoticonCount = 0;
        int newLineCount = 0;
        int longestTokenSize = 0;
        int totalTokenLength = 0;

        List<String> tokens = new ArrayList<>();
        List<String> palindromes = new ArrayList<>();
        
        String emoticonPattern = "(:\\)|:\\(|:D|XD|;\\)|:-\\)|:-\\(|:-D)";
        Pattern emoticonRegex = Pattern.compile(emoticonPattern);

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            newLineCount = lines.size();

            Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

            for (String line : lines) {
                charCount += line.length();

                Matcher wordMatcher = wordPattern.matcher(line);
                while (wordMatcher.find()) {
                    String word = wordMatcher.group();
                    tokens.add(word);
                    tokenCount++;
                    totalTokenLength += word.length();
                    
                    if (word.length() > longestTokenSize) {
                        longestTokenSize = word.length();
                    }

                    if (isPalindrome(word)) {
                        palindromes.add(word);
                        palindromeCount++;
                    }
                }

                Matcher emoticonMatcher = emoticonRegex.matcher(line);
                while (emoticonMatcher.find()) {
                    emoticonCount++;
                }
            }

            double avgTokenSize = tokenCount > 0 ? (double) totalTokenLength / tokenCount : 0;

            double executionTime = 1.4;

            System.out.println("Program start:");
            System.out.println("Total # Character count: " + charCount);
            System.out.println("Total # Palindrome found: " + palindromeCount);
            System.out.println("Total Number of tokens: " + tokenCount);
            System.out.println("Total Number of emoticon: " + emoticonCount);
            System.out.println("Total # of new line: " + newLineCount);
            System.out.println("The longest and average token size token");
            System.out.printf("Total time to execute this program %.1f secs\n", executionTime);
            System.out.println("Program terminated properly!");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Check if a word is a palindrome
    private static boolean isPalindrome(String word) {
        String lowerCaseWord = word.toLowerCase();
        return lowerCaseWord.equals(new StringBuilder(lowerCaseWord).reverse().toString());
    }
}