package predictive;

import java.io.File;
import java.util.*;

/**
 * @author Peng Dai
 * @version 2020/2/17
 *
 * <p>
 * This class can read and store the dictionary in memory as a list of pairs.
 * As the list will be sorted and in memory, a faster look-up technique can be used.
 */
public class ListDictionary implements Dictionary {
    private ArrayList<WordSig> dictionary = new ArrayList<>();

    /**
     * The getter for dictionary.
     *
     * @return The instance's dictionary.
     */
    public ArrayList<WordSig> getDictionary() {
        return dictionary;
    }

    /**
     * A constructor that takes a String path to the dictionary, reads stores it in an ArrayList.
     * Each entry of the ArrayList is a object of class WordSig, consisting of the word that has
     * been read in and its signature.
     *
     * @param path The String path to the dictionary
     */
    public ListDictionary(String path) {
        File dictionaryF = new File(path);
        try (
                Scanner in = new Scanner(dictionaryF);
        ) {
            while (in.hasNext()) {
                String line = in.next();
                if (isValidWord(line)) {
                    dictionary.add(new WordSig(line.toLowerCase(), wordToSignature(line)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(dictionary);
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
     * This method takes the given numeric signature and returns a set of
     * possible matching words from the stored dictionary. The returned list does
     * not have duplicates and each word is in lower-case.
     *
     * @param signature The given numeric signature
     * @return A set of possible matching words from the dictionary
     */
    public Set<String> signatureToWords(String signature) {
        Set<String> words = new HashSet<>();
        WordSig ws = new WordSig("", signature);

        int index1 = Collections.binarySearch(dictionary, ws);
        if (index1 < 0) {
            return words;
        }

        ListIterator<WordSig> aIter = dictionary.listIterator(index1);// listIterator(index1) is the position before index1
        WordSig temp;

        while ((temp = aIter.previous()).getSignature().equals(signature)) {
            //System.out.println(temp.getWords());
        }

        aIter.next();
        while ((temp = aIter.next()).getSignature().equals(signature)) {
            //System.out.println(temp.getWords());
            words.add(temp.getWords());
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


//
//    int binarySearch(int start, int end, String signature) {
//        int mid = (start + end) / 2;
//        if (dictionary.get(mid).getSignature().compareTo(signature) == 0) {
//            return mid;
//        } else if (dictionary.get(mid).getSignature().compareTo(signature) > 0) {
//            return binarySearch(start, mid, signature);
//        } else {
//            return binarySearch(mid + 1, end, signature);
//        }
//    }

}
