package predictive;

import java.io.File;
import java.util.*;

/**
 * @author Peng Dai
 * @version 2020/2/17
 * <p>
 * This class forms a recursive data structure called TreeDictionary. This tree has eight branches,
 * one for each number (2-9) that is allowed in a signature. Each path of the tree (from the root to a node)
 * represents a signature or part of a signature. At each node of the tree, there is a collection of all the
 * words that can possibly match the partial signature along the path. That means that every word that has a
 * prefix corresponding to the partial signature appears in the collection. For example, if the dictionary
 * has the words a, ant and any, then the words at nodes corresponding to paths would be as follows:
 * • at node 2, we have a, ant and any,
 * • at node 2, 6, we have ant and any.
 * • at node 2, 6, 8, we have only ant.
 */
public class TreeDictionary {

    private boolean empty;
    private HashSet<String> words;
    private TreeDictionary two;
    private TreeDictionary three;
    private TreeDictionary four;
    private TreeDictionary five;
    private TreeDictionary six;
    private TreeDictionary seven;
    private TreeDictionary eight;
    private TreeDictionary nine;


    /**
     * A constructor that takes a String path to the dictionary, reads and stores it in a TreeDictionary.
     *
     * @param path The String path to the dictionary
     */
    public TreeDictionary(String path) {
        this.words = new HashSet<String>();
        this.two = new TreeDictionary();
        this.three = new TreeDictionary();
        this.four = new TreeDictionary();
        this.five = new TreeDictionary();
        this.six = new TreeDictionary();
        this.seven = new TreeDictionary();
        this.eight = new TreeDictionary();
        this.nine = new TreeDictionary();
        this.empty = false;
        File dictionaryF = new File(path);
        try (
                Scanner in = new Scanner(dictionaryF);
        ) {
            while (in.hasNext()) {
                String line = in.next();
                if (isValidWord(line)) {
                    line = line.toLowerCase();
                    String sig = wordToSignature(line);
                    add(line, sig, this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to add word and signature in a TreeDictionary object.
     *
     * @param word The word to be added
     * @param sig  The signature correspond to the given word
     * @param td   The TreeDictionary to which the word and signature is added
     */
    public void add(String word, String sig, TreeDictionary td) {
        int length = sig.length();
        if (length == 0) {
            td = new TreeDictionary();
            return;
        }
        String first = sig.substring(0, 1);

        if (first.equals("2")) {
            if (td.two.isEmpty()) {
                td.two = new TreeDictionary(new HashSet<String>());
            }
            td.two.getWords().add(word);
            add(word, sig.substring(1, length), td.two);
        } else if (first.equals("3")) {
            if (td.three.isEmpty()) {
                td.three = new TreeDictionary(new HashSet<String>());
            }
            td.three.getWords().add(word);
            add(word, sig.substring(1, length), td.three);
        } else if (first.equals("4")) {
            if (td.four.isEmpty()) {
                td.four = new TreeDictionary(new HashSet<String>());
            }
            td.four.getWords().add(word);
            add(word, sig.substring(1, length), td.four);
        } else if (first.equals("5")) {
            if (td.five.isEmpty()) {
                td.five = new TreeDictionary(new HashSet<String>());
            }
            td.five.getWords().add(word);
            add(word, sig.substring(1, length), td.five);
        } else if (first.equals("6")) {
            if (td.six.isEmpty()) {
                td.six = new TreeDictionary(new HashSet<String>());
            }
            td.six.getWords().add(word);
            add(word, sig.substring(1, length), td.six);
        } else if (first.equals("7")) {
            if (td.seven.isEmpty()) {
                td.seven = new TreeDictionary(new HashSet<String>());
            }
            td.seven.getWords().add(word);
            add(word, sig.substring(1, length), td.seven);
        } else if (first.equals("8")) {
            if (td.eight.isEmpty()) {
                td.eight = new TreeDictionary(new HashSet<String>());
            }
            td.eight.getWords().add(word);
            add(word, sig.substring(1, length), td.eight);
        } else if (first.equals("9")) {
            if (td.nine.isEmpty()) {
                td.nine = new TreeDictionary(new HashSet<String>());
            }
            td.nine.getWords().add(word);
            add(word, sig.substring(1, length), td.nine);
        }
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
     * @return A number corresponding to the character
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
     * possible matching words from the stored dictionary. The returned set does
     * not have duplicates and each word is in lower-case.
     *
     * @param signature The given numeric signature
     * @return A set of possible matching words from the dictionary
     */
    public Set<String> signatureToWords(String signature) {
        if (signature.length() == 0) {
            return new HashSet<String>();
        }

        return trim(signature.length(),find(signature, this).getWords());
    }



    /**
     * A helper method for signatureToWords to find the specific subtree corresponding to the given signature.
     *
     * @param sig The given signature
     * @param td  The TreeDictionary from which the signature is searched
     * @return The specific subtree corresponding to the given signature
     */
    private TreeDictionary find(String sig, TreeDictionary td) {
        int length = sig.length();
        if (td.isEmpty()) {
            return new TreeDictionary(new HashSet<>());
        }
        if (sig.length() == 0) {
            return td;
        }
        String first = sig.substring(0, 1);
        if (first.equals("2")) {
            return find(sig.substring(1, length), td.two);
        } else if (first.equals("3")) {
            return find(sig.substring(1, length), td.three);
        } else if (first.equals("4")) {
            return find(sig.substring(1, length), td.four);
        } else if (first.equals("5")) {
            return find(sig.substring(1, length), td.five);
        } else if (first.equals("6")) {
            return find(sig.substring(1, length), td.six);
        } else if (first.equals("7")) {
            return find(sig.substring(1, length), td.seven);
        } else if (first.equals("8")) {
            return find(sig.substring(1, length), td.eight);
        } else if (first.equals("9")) {
            return find(sig.substring(1, length), td.nine);
        }
        return new TreeDictionary(new HashSet<>());
    }

    /**
     * A helper method to trim the output of signatureToWords.
     * @param length The limited length.
     * @param words The set which has store all the String in different length.
     * @return A new Set which has store all the String in same length.
     */
    public Set<String> trim(int length, Set<String> words){
        Set<String> trimmed = new HashSet<String>();
        for(String s:words) {
            trimmed.add(s.substring(0,length));
        }
        return trimmed;

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

    /**
     * Creates a new TreeDictionary with root words and 8 subtrees.
     */
    public TreeDictionary(HashSet<String> words, TreeDictionary two, TreeDictionary three, TreeDictionary four, TreeDictionary five, TreeDictionary six, TreeDictionary seven, TreeDictionary eight, TreeDictionary nine) {
        this.empty = false;
        this.words = words;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
    }

    /**
     * Creates a TreeDictionary with a single leaf node
     */
    public TreeDictionary(HashSet<String> words) {
        this.empty = false;
        this.words = words;
        this.two = new TreeDictionary();
        this.three = new TreeDictionary();
        this.four = new TreeDictionary();
        this.five = new TreeDictionary();
        this.six = new TreeDictionary();
        this.seven = new TreeDictionary();
        this.eight = new TreeDictionary();
        this.nine = new TreeDictionary();
    }

    /**
     * Creates an empty tree
     */
    public TreeDictionary() {
        this.empty = true;
        this.words = null;
        this.two = null;
        this.three = null;
        this.four = null;
        this.five = null;
        this.six = null;
        this.seven = null;
        this.eight = null;
        this.nine = null;
    }


    /**
     * returns true if this TreeDictionary is empty
     */
    public boolean isEmpty() {
        return empty;
    }


    /**
     * gets the root words of this tree
     */
    public HashSet<String> getWords() {
        return words;
    }

    /**
     * The getter for subtree 'two'.
     *
     * @return The subtree 'two' is returned
     */
    public TreeDictionary getTwo() {
        return two;
    }

    /**
     * The getter for subtree 'three'.
     *
     * @return The subtree 'three' is returned
     */
    public TreeDictionary getThree() {
        return three;
    }

    /**
     * The getter for subtree 'four'.
     *
     * @return The subtree 'four' is returned
     */
    public TreeDictionary getFour() {
        return four;
    }

    /**
     * The getter for subtree 'five'.
     *
     * @return The subtree 'five' is returned
     */
    public TreeDictionary getFive() {
        return five;
    }

    /**
     * The getter for subtree 'six'.
     *
     * @return The subtree 'six' is returned
     */
    public TreeDictionary getSix() {
        return six;
    }

    /**
     * The getter for subtree 'seven'.
     *
     * @return The subtree 'seven' is returned
     */
    public TreeDictionary getSeven() {
        return seven;
    }

    /**
     * The getter for subtree 'eight'.
     *
     * @return The subtree 'eight' is returned
     */
    public TreeDictionary getEight() {
        return eight;
    }

    /**
     * The getter for subtree 'nine'.
     *
     * @return The subtree 'nine' is returned
     */
    public TreeDictionary getNine() {
        return nine;
    }

}
