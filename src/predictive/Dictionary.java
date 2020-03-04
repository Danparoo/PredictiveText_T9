package predictive;

import java.util.Set;


/**
 * @author Peng Dai
 * @version 2020/2/17
 * <p>
 * An interface which the dictionary classes should implement.
 */
public interface Dictionary {
    public Set<String> signatureToWords(String signature);
}
