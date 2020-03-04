package predictive;

import java.io.File;
import java.util.*;


/**
 * @author Peng Dai
 * @version 2020/2/17
 * This class can read and store the dictionary in memory using Map data structure.
 * <p>
 * Explain how the map works and justify your choice in a comment.
 * A map stores key/value pairs. We can find a value if we provide the key.
 * In this case, since the order of entry is not necessary, I choose HashMap for higher speed.
 */
public class MapDictionary implements Dictionary {
    private HashMap<String, HashSet<String>> dictionary = new HashMap<>();

    /**
     * The getter for dictionary.
     *
     * @return The instance's dictionary.
     */
    public HashMap<String, HashSet<String>> getDictionary() {
        return dictionary;
    }


    /**
     * A constructor that takes a String path to the dictionary, reads stores it in an HashMap.
     * Each entry of the HashMap is a data structure that maps each signature to set of words.
     *
     * @param path The String path to the dictionary
     */
    public MapDictionary(String path) {
        File dictionaryF = new File(path);
        try (
                Scanner in = new Scanner(dictionaryF);
        ) {
            while (in.hasNext()) {
                String line = in.next();
                if (isValidWord(line)) {
                    String sig = wordToSignature(line);
                    HashSet<String> setOfSig = dictionary.getOrDefault(sig, new HashSet<String>());
                    setOfSig.add(line.toLowerCase());
                    dictionary.put(sig, setOfSig);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * This method takes the given numeric signature and returns a set of
     * possible matching words from the stored dictionary. If this signature
     * isn't stored in the dictionary, it will return a empty set.
     *
     * @param signature The given numeric signature
     * @return A set of possible matching words from the dictionary
     */
    public Set<String> signatureToWords(String signature) {
        return dictionary.getOrDefault(signature, new HashSet<String>());
    }


    /**
     * This method takes a word and returns a numeric signature. For example,
     * “home” should return “4663”. If the word has any non-alphabetic characters,
     * replace them with a “ ” (space) in the resulting signature.
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
