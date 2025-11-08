import java.io.*;
import java.util.*;

public class VowelCountFromFile {

    public static void main(String[] args) {

        // Store count of each vowel
        Map<Character, Integer> vowelCount = new HashMap<>();
        vowelCount.put('a', 0);
        vowelCount.put('e', 0);
        vowelCount.put('i', 0);
        vowelCount.put('o', 0);
        vowelCount.put('u', 0);

        int totalVowels = 0;

        try {
            FileReader fr = new FileReader("notes.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                line = line.toLowerCase();

                for (char c : line.toCharArray()) {
                    if (vowelCount.containsKey(c)) {
                        vowelCount.put(c, vowelCount.get(c) + 1);
                        totalVowels++;
                    }
                }
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Determine most frequent vowel
        char mostFrequent = 'a';
        int maxCount = 0;

        for (char v : vowelCount.keySet()) {
            if (vowelCount.get(v) > maxCount) {
                maxCount = vowelCount.get(v);
                mostFrequent = v;
            }
        }

        // Display result
        System.out.println("Total vowels: " + totalVowels);
        System.out.println("Vowel counts: " + vowelCount);
        System.out.println("Most frequent vowel: '" + mostFrequent + "' appears " + maxCount + " times");
    }
}
