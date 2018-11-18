import java.util.ArrayList;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    // Initializes the data structure using the
    // given array of strings as the dictionary.
    //
    // (You can assume each word in the dictionary
    // contains only the uppercase letters A through Z.)

    /**
     * TST to store values.
     */
    private TST<Integer> tstDict;
    /**
     * Constructs the object.
     * Complexity is O(N)
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        tstDict = new TST<Integer>();
        for (int i = 0; i < dictionary.length; i++) {
            tstDict.put(dictionary[i], i);
        }
    }

    /**
     * Gets all valid words.
     * Returns the set of all valid words in the given Boggle board.
     * Complexity is O(N^2)
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        ArrayList<String> arraylist = new ArrayList<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                String str = "";
                dfs(board, arraylist, marked, i, j, str);
            }
        }
        return arraylist;
    }
    /**
     * Depth first search.
     * Complexity is O(V + E)
     *
     * @param      board      The board
     * @param      arraylist  The arraylist
     * @param      marked     The marked
     * @param      row        The row
     * @param      col        The col
     * @param      str1        The string
     */
    public void dfs(final BoggleBoard board, final ArrayList<String> arraylist,
                    final boolean[][] marked,
                    final int row, final int col, final String str1) {
        String str = str1;
        marked[row][col] = true;

        if (board.getLetter(row, col) == 'Q') {
            str += board.getLetter(row, col) + "U";
        } else {
            str += board.getLetter(row, col);
        }

        if (!tstDict.hasPrefix(str)) {
            return;
        }

        if (str.length() > 2 && tstDict.contains(str)) {
            if (!arraylist.contains(str)) {
                arraylist.add(str);
            }
        }
        for (int i = row - 1; i < row + 2; i++) { // i < row-1 + 3
            for (int j = col - 1; j < col + 2; j++) {
                if ((i >= 0 && i < board.rows() && j >= 0 && j < board.cols())
                        && !(marked[i][j])) {
                    dfs(board, arraylist, marked, i, j, str);
                    marked[i][j] = false;
                }
            }
        }
    }

    /**
     * Returns the score of the given word if it is in the dictionary,
     * zero otherwise.
     * Complexity is O(1)
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        final int one = 1;
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int eleven = 11;

        if (word.length() >= eight) {
            return eleven;
        } else if (word.length() == seven) {
            return five;
        } else if (word.length() == six) {
            return three;
        } else if (word.length() == five) {
            return two;
        } else if (word.length() == three || word.length() == four) {
            return one;
        } else {
            return 0;
        }
    }
}


