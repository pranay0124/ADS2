import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	TST<Integer> tst;
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		tst = new TST<Integer>();
		for (int i = 0; i < dictionary.length; i++) {
			tst.put(dictionary[i], i);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		ArrayList<String> bag = new ArrayList<String>();
		boolean[][] marked = new boolean[board.rows()][board.cols()];
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				marked[i][j] = true;
				String str = board.getLetter(i, j) + "";
				dfs(i, j, str, marked, board, bag);
			}
		}
		return bag;
	}

	public void dfs(int row, int col, String str, boolean[][] marked, BoggleBoard board, ArrayList<String> bag) {
		if (str.length() > 2 && tst.contains(str)) {
			if (!bag.contains(str)) {
				bag.add(str);
			}
		}
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				if (marked[i][j]) {
					continue;
				}
				marked[i][j] = true;
				str += board.getLetter(i, j) + "";
				dfs(i, j, str, marked, board, bag);
			}
		}
	}


// Returns the score of the given word if it is in the dictionary, zero otherwise.
// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		int a = word.length();
		if (a >= 0 || a <= 2) {
			return 0;
		} else if (a >= 3 || a <= 4) {
			return 1;
		} else if (a == 5) {
			return 2;
		} else if (a == 6) {
			return 3;
		} else if (a == 7) {
			return 5;
		} else {
			return 11;
		}
	}
}
