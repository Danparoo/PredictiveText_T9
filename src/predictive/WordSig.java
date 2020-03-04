package predictive;

/**
 * @author Peng Dai
 * @version 2020/2/17
 * A class named WordSig that pairs words and signatures
 */
public class WordSig implements Comparable<WordSig> {

    private String words;
    private String signature;

    /**
     * The constructor.
     *
     * @param words     The alphabetic word.
     * @param signature A numeric signature of the word.
     */
    public WordSig(String words, String signature) {
        this.words = words;
        this.signature = signature;
    }


    /**
     * The compareTo method to implement the Comparable interface.
     *
     * @param ws The argument object to be compared
     * @return An integer according to whether the current object is less than,
     * equal to, or greater than the argument object
     */
    public int compareTo(WordSig ws) {
//        if (this.getSignature().compareTo(ws.getSignature()) == 0) {
//            return this.getWords().compareTo(ws.getWords());
//        }
        return this.getSignature().compareTo(ws.getSignature());
    }


    /**
     * The getter for words
     *
     * @return The words of this object
     */
    public String getWords() {
        return words;
    }

    /**
     * The getter for signature.
     *
     * @return The signature of this object
     */
    public String getSignature() {
        return signature;
    }

}
