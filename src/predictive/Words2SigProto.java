package predictive;

/**
 * @author Peng Dai
 * @version 2020/2/17
 * <p>
 * A command-line programs (classes with main methods) for calling the wordToSignature method
 * The output can be used to test Sigs2WordsProto.
 */
public class Words2SigProto {
    public static void main(String[] args) {
        for (String sig : args) {
            System.out.print(PredictivePrototype.wordToSignature(sig + " "));
        }
    }
}
