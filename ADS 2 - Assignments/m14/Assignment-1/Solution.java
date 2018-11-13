import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] words = loadWords();
		TST<Integer> tst = new TST<Integer>();
		for (int i=0;i<words.length;i++) {
			for(int j=0;j<words[i].length();j++) {
				String str = words[i].substring(j);
				if(!tst.contains(str)) {
					tst.put(str, i);
				}
			}
		}
		String input = scan.nextLine();
		for(String s : tst.keysWithPrefix(input)) {
			System.out.println(s);
		}
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}