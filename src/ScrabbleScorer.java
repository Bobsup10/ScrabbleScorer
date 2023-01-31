import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Srabble Scorer
 * @version 1.30.23
 * @author 24rossilli
 */
public class ScrabbleScorer {
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary;
    /**
     * Creates an Array for the words in the dictionary
     */
    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        buildDictionary();

    }
    /**
     * Reads the dictionary from the SRABBLE_WORDS.txt file
     */
    public void buildDictionary() {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while (in.hasNext()) {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0, 1));
                dictionary.get(index).add(word);

            }
            in.close();
            // now I need to sort all buckets
            for (int i = 0; i < dictionary.size(); i++)
                Collections.sort(dictionary.get(i));

        } catch (Exception e) {
            System.out.println("Error here: " + e);
        }
    }
    /**
     * Determines if the word entered by the user is a word in the dictionary
     * @returns true if valid, false if not valid
     * @param word
     */
    public boolean isValidWord(String word) {
        //find the correct bucket for word by looking at the first letter
        //find indexOf the first letter in alpha
        //get corresponding bucket
        //if(Collections.binarySearch(bucket, word) < -)
        //   return false;
        if (Collections.binarySearch(dictionary.get(alpha.indexOf(word.substring(0, 1))), word) < 0)
            return false;
        return true;
    }
    /**
     * Takes the word and scores them using the values array
     * @returns the score of the word
     * @param word
     */
    public int getWordScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = alpha.indexOf(word.substring(i, i + 1));
            score += values[index];


        }
        return score;
    }
    /**
     * Main method for the Scrabble class
     * @param args
     */
    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a word to score or 0 to quit: ");
            String word = in.nextLine().toUpperCase();
            if (word.equals("0") || word.length() == 0) {
                break;
            }


                if (app.isValidWord(word)) {
                    System.out.println(word + " = " + app.getWordScore(word) + " points");
                } else
                    System.out.println(word + " is not a valid word in the dictionary");


        }
        System.out.println("Exiting the program thanks for playing");
    }
}

