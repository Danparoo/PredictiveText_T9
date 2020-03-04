package predictive;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author <Peng Dai>
 * @version 2020/2/17
 * *** This test may not be passed if the file "words" is changed ***
 * This class contains the test cases for TreeDictionary.
 */
public class TreeDictionaryTest {
    //testing matchSignature
    @Test
    public void test1() {
        String actual = TreeDictionary.matchSignature("a");
        String expected = "2";
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String actual = TreeDictionary.matchSignature("w");
        String expected = "9";
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        String actual = TreeDictionary.matchSignature(",");
        String expected = " ";
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        String actual = TreeDictionary.matchSignature("2");
        String expected = " ";
        assertEquals(expected, actual);
    }

    // testing wordToSignature
    @Test
    public void test5() {
        String actual = TreeDictionary.wordToSignature("abc");
        String expected = "222";
        assertEquals(expected, actual);
    }

    @Test
    public void test6() {
        String actual = TreeDictionary.wordToSignature("hello");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test7() {
        String actual = TreeDictionary.wordToSignature("heLlo");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test8() {
        String actual = TreeDictionary.wordToSignature("hell0");
        String expected = "4355 ";
        assertEquals(expected, actual);
    }

    //testing isValidWord
    @Test
    public void test9() {
        Boolean actual = TreeDictionary.isValidWord("hell0");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void test10() {
        Boolean actual = TreeDictionary.isValidWord("hello");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void test11() {
        Boolean actual = TreeDictionary.isValidWord("danparoo");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //signatureToWords
    @Test
    public void test12() {
        TreeDictionary d1 = new TreeDictionary("src/predictive/words");
        Set<String> actual = d1.signatureToWords("4663687");
        Set<String> expected = new HashSet<>();
        expected.add("homeotr");
        assertEquals(expected, actual);
    }

    @Test
    public void test13() {
        TreeDictionary d1 = new TreeDictionary("src/predictive/words");
        Set<String> actual = d1.signatureToWords("32976642");
        Set<String> expected = new HashSet<>();
        expected.add("dawsonia");
        assertEquals(expected, actual);
    }

    @Test
    public void test14() {
        TreeDictionary d1 = new TreeDictionary("src/predictive/words");
        Set<String> actual = d1.signatureToWords("132976642");
        Set<String> expected = new HashSet<>();
        assertEquals(expected, actual);
    }
}
