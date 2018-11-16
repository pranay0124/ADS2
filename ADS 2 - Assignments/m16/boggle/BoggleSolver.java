import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TST<Integer> tstDict;

	public BoggleSolver(String[] dictionary) {
		tstDict = new TST<Integer>();
		for (int i = 0; i < dictionary.length; i++) {
			tstDict.put(dictionary[i], i);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
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

	public void dfs(BoggleBoard board, ArrayList<String> arraylist, boolean[][] marked, int row, int col, String str) {
		marked[row][col] = true;
		if (board.getLetter(row, col) == 'Q') {
			str += board.getLetter(row, col) + "u";
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
		for (int i = row - 1; i < row - 1 + 3; i++) { // i < row-1 + 3
			for (int j = col - 1; j < col - 1 + 3; j++) {
				if (validate(i, j, board) && !(marked[i][j])) {
					dfs(board, arraylist, marked, i, j, str);
					marked[i][j] = false;
				}
			}
		}
	}
	public boolean validate(int i, int j, BoggleBoard board) {
		if (i >= 0 && i < board.rows() && j >= 0 && j < board.cols()) {
			return true;
		}
		return false;
	}


// Returns the score of the given word if it is in the dictionary, zero otherwise.
// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (word.length() > 8) {
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
