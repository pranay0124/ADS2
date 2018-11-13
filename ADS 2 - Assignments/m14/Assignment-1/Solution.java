/**
 * @author : Pranay Kumar Y.
 * Date : 13th November,2018.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] words = loadWords();
        TST<Integer> tst = new TST<Integer>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                String str = words[i].substring(j);
                if (!tst.contains(str)) {
                    tst.put(str, i);
                }
            }
        }
        String input = scan.nextLine();
        for (String s : tst.keysWithPrefix(input)) {
            System.out.println(s);
        }
    }

    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}

