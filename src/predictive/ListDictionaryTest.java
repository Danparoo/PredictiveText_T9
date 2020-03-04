package predictive;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <Peng Dai>
 * @version 2020/2/17
 * *** This test may not be passed if the file "words" is changed ***
 * This class contains the test cases for ListDictionary.
 */
public class ListDictionaryTest {
    //testing matchSignature
    @Test
    public void test1() {
        String actual = ListDictionary.matchSignature("a");
        String expected = "2";
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String actual = ListDictionary.matchSignature("w");
        String expected = "9";
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        String actual = ListDictionary.matchSignature(",");
        String expected = " ";
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        String actual = ListDictionary.matchSignature("2");
        String expected = " ";
        assertEquals(expected, actual);
    }

    // testing wordToSignature
    @Test
    public void test5() {
        String actual = ListDictionary.wordToSignature("abc");
        String expected = "222";
        assertEquals(expected, actual);
    }

    @Test
    public void test6() {
        String actual = ListDictionary.wordToSignature("hello");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test7() {
        String actual = ListDictionary.wordToSignature("heLlo");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test8() {
        String actual = ListDictionary.wordToSignature("hell0");
        String expected = "4355 ";
        assertEquals(expected, actual);
    }

    //testing isValidWord
    @Test
    public void test9() {
        Boolean actual = ListDictionary.isValidWord("hell0");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void test10() {
        Boolean actual = ListDictionary.isValidWord("hello");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void test11() {
        Boolean actual = ListDictionary.isValidWord("danparoo");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //signatureToWords
    @Test
    public void test12() {
        ListDictionary d1 = new ListDictionary("src/predictive/words");
        Set<String> actual = d1.signatureToWords("329");
        Set<String> expected = new HashSet<>();
        expected.add("dbw");
        expected.add("dax");
        expected.add("daw");
        expected.add("fax");
        expected.add("day");
        expected.add("fcy");
        expected.add("fay");
        assertEquals(expected, actual);
    }

    @Test
    public void test13() {
        ListDictionary d1 = new ListDictionary("src/predictive/words");
        Set<String> actual = d1.signatureToWords("4663");
        Set<String> expected = new HashSet<>();
        expected.add("hood");
        expected.add("ione");
        expected.add("ioof");
        expected.add("good");
        expected.add("hond");
        expected.add("inne");
        expected.add("gond");
        expected.add("hone");
        expected.add("hoof");
        expected.add("gone");
        expected.add("goof");
        expected.add("home");
        expected.add("gome");
        assertEquals(expected, actual);
    }
}
