package predictive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;


/**
 * @author <Peng Dai>
 * @version 2020/2/17
 * *** This test may not be passed if the file "words" is changed ***
 * This class contains the test cases for PredictivePrototype.
 */
public class PredictivePrototypeTest {

    //testing matchSignature
    @Test
    public void test1() {
        String actual = PredictivePrototype.matchSignature("a");
        String expected = "2";
        assertEquals(expected, actual);
    }

    @Test
    public void test2() {
        String actual = PredictivePrototype.matchSignature("w");
        String expected = "9";
        assertEquals(expected, actual);
    }

    @Test
    public void test3() {
        String actual = PredictivePrototype.matchSignature(",");
        String expected = " ";
        assertEquals(expected, actual);
    }

    @Test
    public void test4() {
        String actual = PredictivePrototype.matchSignature("2");
        String expected = " ";
        assertEquals(expected, actual);
    }

    // testing wordToSignature
    @Test
    public void test5() {
        String actual = PredictivePrototype.wordToSignature("abc");
        String expected = "222";
        assertEquals(expected, actual);
    }

    @Test
    public void test6() {
        String actual = PredictivePrototype.wordToSignature("hello");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test7() {
        String actual = PredictivePrototype.wordToSignature("heLlo");
        String expected = "43556";
        assertEquals(expected, actual);
    }

    @Test
    public void test8() {
        String actual = PredictivePrototype.wordToSignature("hell0");
        String expected = "4355 ";
        assertEquals(expected, actual);
    }

    //testing isValidWord
    @Test
    public void test9() {
        Boolean actual = PredictivePrototype.isValidWord("hell0");
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void test10() {
        Boolean actual = PredictivePrototype.isValidWord("hello");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void test11() {
        Boolean actual = PredictivePrototype.isValidWord("danparoo");
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    //signatureToWords
    @Test
    public void test12() {
        Set<String> actual = PredictivePrototype.signatureToWords("329");
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
        Set<String> actual = PredictivePrototype.signatureToWords("4663");
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
