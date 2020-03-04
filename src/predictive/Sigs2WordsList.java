package predictive;


/**
 * @author Peng Dai
 * @version 2020/2/17
 * A command-line program for testing the
 * ListDictionary class.
 * <p>
 * * Timing Comparisons between Words2SigProto and Sigs2WordsList *
 * <p>
 * 1st data used: simply "4663 329"
 * Words2SigProto took 0.869s to complete.
 * Sigs2WordsList took 6.200s to compelte.
 * It's surprising since Sigs2WordsList is much more slow.
 * (It is due to the fact that the constructor of ListDictionary took a lot of time to build an ArryList)
 * <p>
 * <p>
 * 2nd data used(article):The company's hardware products include the iPhone smartphone, the iPad tablet computer,
 * the Mac personal computer, the iPod portable media player, the Apple Watch smartwatch, the Apple TV digital
 * media player, the AirPods wireless earbuds and the HomePod smart speaker. Apple's software includes the macOS,
 * iOS, iPadOS, watchOS, and tvOS operating systems, the iTunes media player, the Safari web browser, the Shazam
 * acoustic fingerprint utility, and the iLife and iWork creativity and productivity suites, as well as professional
 * applications like Final Cut Pro, Logic Pro, and Xcode. Its online services include the iTunes Store, the iOS App
 * Store, Mac App Store, Apple Music, Apple TV+, iMessage, and iCloud. Other services include Apple Store, Genius Bar,
 * AppleCare, Apple Pay, Apple Pay Cash, and Apple Card.
 * After using Words2SigProto: 843 2667269 7 42739273 77638287 4625833 843 474663 7627874663  843 4723 822538 26678837
 * 843 622 73776625 26678837  843 4763 76782253 63342 752937  843 27753 92824 7627892824  843 27753 88 3444825 63342
 * 752937 843 2477637 94735377 3272837 263 843 4663763 76278 7732537  27753 7 76389273 46258337 843 62267  467  472367
 * 9282467  263 8867 673728464 7978367  843 488637 63342 752937  843 723274 932 2769737  843 742926 22687842 34643777468
 * 8845489  263 843 45433 263 49675 2732848489 263 776382848489 784837  27 9355 27 776337746625 277542284667 5453 34625
 * 288 776  56442 776  263 92633  487 665463 73784237 4625833 843 488637 78673  843 467 277 78673  622 277 78673  27753
 * 68742  27753 88   46377243  263 425683  68437 73784237 4625833 27753 78673  436487 227  277532273  27753 729  27753
 * 729 2274  263 27753 2273
 * <p>
 * Words2SigProto took 50.971s to complete.
 * Sigs2WordsList took 6.217s to compelte.
 * <p>
 * This time Sigs2WordsList is much more fast. Because once ArrayList is built, The searching operation would be
 * extreamly fast for Sigs2WordsList. But for Words2SigProto, it still need to traversal every line in the dictionary "words"
 */
public class Sigs2WordsList {
    public static void main(String[] args) {


        ListDictionary d1 = new ListDictionary("src/predictive/words");

        for (String sig : args) {
            System.out.print(sig + ": ");
            for (String word : d1.signatureToWords(sig)) {
                System.out.print(word + " ");
            }
            System.out.println();
        }


    }

}
