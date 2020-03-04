package predictive;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Peng Dai
 * @version 2020/2/17
 * <p>
 * This class building a “prototype” for the predictive text problem, which is not efficient,
 * but it is simple.
 */
public class PredictivePrototype {

    /**
     * This method takes a word and returns a numeric signature. For example,
     * “home” should return “4663”. If the word has any non-alphabetic characters,
     * replace them with a “ ” (space) in the resulting signature.
     * <p>
     * // Why StringBuffer is more efficient than String?
     * String is immutable in Java. Once initialized, the length cannot be modified.
     * Simple string concatenation is actually creating a new String object, and then
     * assigning the concatenated content to the new object.It will be frequent in the
     * case of frequent modification. Create objects. StringBuilder does not. There
     * is only one instance object from beginning to end.
     * How does StringBuilder implement it?
     * In fact, StringBuilder does not use String storage when appending, but puts it
     * into a char array of value, and the array can be expanded, so there is no need
     * to constantly create objects.
     *
     * @param word The original word to be translate.
     * @return A numeric signature of the word
     */
    public static String wordToSignature(String word) {
        StringBuffer words = new StringBuffer(word);
        StringBuffer sig = new StringBuffer();

        for (int i = 0; i < word.length(); i++) {
            sig.append(matchSignature(words.substring(i, i + 1)));
        }
        return sig.toString();
    }

    /**
     * The helper method for wordToSignature. This method take one character
     * and translate it to a number.
     *
     * @param character The one character to be translated
     * @return A number correspond to the character
     */
    public static String matchSignature(String character) {
        if (character.matches("[a-cA-C]+")) {
            return "2";
        } else if (character.matches("[d-fD-F]+")) {
            return "3";
        } else if (character.matches("[g-iG-I]+")) {
            return "4";
        } else if (character.matches("[j-lJ-L]+")) {
            return "5";
        } else if (character.matches("[m-oM-O]+")) {
            return "6";
        } else if (character.matches("[p-sP-S]+")) {
            return "7";
        } else if (character.matches("[t-vT-V]+")) {
            return "8";
        } else if (character.matches("[w-zW-Z]+")) {
            return "9";
        }
        return " ";
    }


    /**
     * This method takes the given numeric signature and returns a set of
     * possible matching words from the dictionary. The returned list does
     * not have duplicates and each word is in lower-case.
     * <p>
     * Why should we not store the dictionary in the Java program?
     * Even after dictionary being stored in the program, the program still
     * needs to compare the words with signature one by one to find the matching words.
     * So stored in the program will not speed up the operation, but wasted memory space
     * and increased space complexity.
     *
     * @param signature The given numeric signature
     * @return A set of possible matching words from the dictionary
     */
    public static Set<String> signatureToWords(String signature) {
        StringBuffer sig = new StringBuffer(signature);
        Set<String> words = new HashSet<>();

        File dictionary = new File("src/predictive/words");
        try (
                Scanner in = new Scanner(dictionary);
        ) {
            while (in.hasNext()) {
                String line = in.next();
                if (line.length() == signature.length() && isValidWord(line) && signature.equals(wordToSignature(line))) {
                    words.add(line.toLowerCase());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }


    /**
     * A method to check if a given word is valid. So we can ignore lines
     * with non-alphabetic characters.
     *
     * @param word The word tbe check.
     * @return A numeric signature of the word.
     */
    static boolean isValidWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!word.substring(i, i + 1).matches("[a-zA-Z]+")) {
                return false;
            }
        }
        return true;
    }

}
