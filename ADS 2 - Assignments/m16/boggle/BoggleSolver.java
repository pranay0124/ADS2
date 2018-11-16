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
    TST<Integer> tstDict;
    /**
     * Constructs the object.
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
     *
     * @param      board      The board
     * @param      arraylist  The arraylist
     * @param      marked     The marked
     * @param      row        The row
     * @param      col        The col
     * @param      str        The string
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
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word.length() >= 8) {
            return 11;
        } else if (word.length() == 7) {
            return 5;
        } else if (word.length() == 6) {
            return 3;
        } else if (word.length() == 5) {
            return 2;
        } else if (word.length() == 3 || word.length() == 4) {
            return 1;
        } else {
            return 0;
        }
    }
}


