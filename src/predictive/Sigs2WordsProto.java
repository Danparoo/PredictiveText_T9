package predictive;

/**
 * @author Peng Dai
 * @version 2020/2/17
 * <p>
 * A command-line programs (classes with main methods) for calling the signatureToWords method from PredictivePrototype.
 * The output of Sigs2WordsProto is formated as one line per signature.
 */
public class Sigs2WordsProto {
    public static void main(String[] args) {

        for (String sig : args) {
            System.out.print(sig + ": ");
            for (String word : PredictivePrototype.signatureToWords(sig)) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
