/**
 * @author : Pranay Kumar Y.
 */
import java.util.Scanner;
import java.util.TreeSet;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    // Don't modify this method.
    /**
     * Main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash =
                loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.

    /**
     * Function to read files.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(
        final String file) {
        BinarySearchST<String, Integer>  st =
            new BinarySearchST<String, Integer>();
        // your code goes here
        String[] tokens = toReadFile(file);
        for (String line : tokens) {
            String[] words = line.toLowerCase().split(" ");
            for (String word : words) {
                if (st.contains(word)) {
                    st.put(word, st.get(word) + 1);
                } else {
                    st.put(word, 1);
                }
            }
        }
        return st;
    }
}

/**
 * Class for t 9.
 */
final class T9 {
    /**
     * TST.
     */
    private TST<Integer> tst;
    /**
     * Constructs the object.
     * Complexity is O(N)
     *
     * @param      st    { parameter_description }
     */
    protected T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<Integer>();
        for (String word : st.keys()) {
            tst.put(word, st.get(word));
        }

    }

    /**
     * Gets all words.
     * get all the prefixes that match with given prefix.
     * Complexity is O(L + ln N)
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
    /**
     * Gets the potential words.
     * Complexity is O(N ^ 2)
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        TreeSet<String> treeset = new TreeSet<>();
        for (String word : tst.keys()) {
            String[] character = word.split("");
            String value = "";
            for (String each : character) {
                if (each.equals("a") || each.equals("b") || each.equals("c")) {
                    value = value + "2";
                }
                if (each.equals("d") || each.equals("e") || each.equals("f")) {
                    value = value + "3";
                }
                if (each.equals("g") || each.equals("h") || each.equals("i")) {
                    value = value + "4";
                }
                if (each.equals("j") || each.equals("k") || each.equals("l")) {
                    value = value + "5";
                }
                if (each.equals("m") || each.equals("n") || each.equals("o")) {
                    value = value + "6";
                }
                if (each.equals("p") || each.equals("q") || each.equals("r")
                        || each.equals("s")) {
                    value = value + "7";
                }
                if (each.equals("t") || each.equals("u") || each.equals("v")) {
                    value = value + "8";
                }
                if (each.equals("w") || each.equals("x") || each.equals("y")
                        || each.equals("z")) {
                    value = value + "9";
                }
            }
            if (value.equals(t9Signature)) {
                treeset.add(word);
            }
        }
        return treeset;
    }

    /**
     * Gets the suggestions.
     * return all possibilities(words), find top k with highest frequency.
     * Complexity is O(N ^ 2)
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
                                           final int k) {
        // your code goes here
        TreeSet<String> treeset = new TreeSet<>();
        MaxPQ<Integer> maxpq = new MaxPQ<>();
        for (String word : words) {
            maxpq.insert(tst.get(word));
        }
        for (int i = 0; i < k; i++) {
            int f = maxpq.delMax();
            for (String word : words) {
                if (f == tst.get(word)) {
                    treeset.add(word);
                }
            }
        }
        // System.out.println("set" + treeset);
        return treeset;
    }
    /**
     * final output.
     * Don't modify this method.
     * Complexity is O(N ^ 2)
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
