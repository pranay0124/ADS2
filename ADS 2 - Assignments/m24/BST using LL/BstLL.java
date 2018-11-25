import java.util.*;
class BSTLLRepresentation<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int size;          // number of nodes in subtree

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	public BSTLLRepresentation() {
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null) return 0;
		else return x.size;
	}

	public boolean contains(Key key) {
		// if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public Key min() {
		// if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		else                return min(x.left);
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		// if (key == null) throw new IllegalArgumentException("called get() with a null key");
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	public void put(Key key, Value val) {
		// if (key == null) throw new IllegalArgumentException("calledput() with a null key");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
		// assert check();
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	public void delete(Key key) {
		// if (key == null) throw new IllegalArgumentException("called delete() with a null key");
		root = delete(root, key);
		// assert check();
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMin() {
		// if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
		// assert check();
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}
}
class BstLL {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of Entries");
		int n = scan.nextInt();
		BSTLLRepresentation<Integer, String> arrayRepresentation = new BSTLLRepresentation<Integer, String>();
		Stopwatch stopwatch = new Stopwatch();
		Double timeForPut = 0.0;
		Double timeForGet = 0.0;
		Double timeForDelete = 0.0;
		while (n >= 0) {
			String s = scan.nextLine();
			String[] tokens = s.split(" ");
			switch (tokens[0]) {
			case"put":
				arrayRepresentation.put(Integer.parseInt(tokens[1]), tokens[2]);
				timeForPut = stopwatch.elapsedTime();
				
				break;
			case"get":
				String val = arrayRepresentation.get(Integer.parseInt(tokens[1]));
				System.out.println(val);
				timeForGet = stopwatch.elapsedTime();
				
				break;
			case"delete":
				arrayRepresentation.delete(Integer.parseInt(tokens[1]));
				timeForDelete = stopwatch.elapsedTime();
				
			}
			n--;
		}
		System.out.println("Time for Put Function: " + timeForPut);
		System.out.println("Time for Get Function: " + timeForGet);
		System.out.println("Time for Delete Function: " + timeForDelete);
	}
}
