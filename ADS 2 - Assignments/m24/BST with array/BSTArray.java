import java.util.*;
class BSTArrayRepresentation<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] values;
	private int[] size;
	private int[] leftLinks;
	private int[] rightLinks;
	// private int size;
	// private int llsize;
	// private int rlsize;

	BSTArrayRepresentation(int size) {
		keys = (Key[]) new Comparable[size];
		values = (Value[]) new Object[size];
		this.size = new int[size];
		leftLinks = new int[size];
		rightLinks = new int[size];
		for (int i = 0; i < size; i++) {
			leftLinks[i] = -1;
			rightLinks[i] = -1;
		}
	}

	public int size() {
		return size(0);
	}

	private int size(int index) {
		if (index == -1) {
			return 0;
		}

		return size[index];
	}

	// public int llsize() {
	// 	return llsize;
	// }

	// public int rlsize() {
	// 	return rlsize;
	// }

	public Key min() {
		if (size() == 0) {
			System.out.println("Empty binary search tree");
		}
		int minIndex = min(0);
		return keys[minIndex];
	}

	private int min(int index) {
		if (leftLinks[index] == -1) {
			return index;
		}
		return min(leftLinks[index]);
	}

	public Value get(Key key) {
		return get(0, key);
	}

	private Value get(int index, Key key) {
		if (index == -1 || keys[index] == null) {
			return null;
		}
		int compare = key.compareTo(keys[index]);
		if (compare < 0) {
			return get(leftLinks[index], key);
		} else if (compare > 0) {
			return get(rightLinks[index], key);
		} else {
			return values[index];
		}
	}

	public void put(Key key, Value value) {
		if (size() == keys.length) {
			System.out.println("Tree is full");
			return;
		}
		put(0, key, value);
	}

	private int put(int index, Key key, Value value) {
		if (index == -1 || keys[index] == null) {
			int nextIndex = size();
			keys[nextIndex] = key;
			values[nextIndex] = value;
			size[nextIndex] = 1;
			// size += 1;
			return nextIndex;
		}

		int compare = key.compareTo(keys[index]);

		if (compare < 0) {
			leftLinks[index] = put(leftLinks[index], key, value);
		} else if (compare > 0) {
			rightLinks[index] = put(rightLinks[index], key, value);
		} else {
			values[index] = value;
		}

		size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
		return index;
	}

	public void delete(Key key) {
		int rootIndex = delete(0, key);
	}

	private int delete(int index, Key key) {
		if (index == -1 || keys[index] == null) {
			return -1;
		}

		int compare = key.compareTo(keys[index]);
		if (compare < 0) {
			int leftIndex = delete(leftLinks[index], key);
			leftLinks[index] = leftIndex;
		} else if (compare > 0) {
			int rightIndex = delete(rightLinks[index], key);
			rightLinks[index] = rightIndex;
		} else {
			keys[index] = null;
			values[index] = null;
			size[index] = 0;

			if (leftLinks[index] == -1) {
				int rightLinkIndex = rightLinks[index];
				rightLinks[index] = -1;
				return rightLinkIndex;
			} else if (rightLinks[index] == -1) {
				int leftLinkIndex = leftLinks[index];
				leftLinks[index] = -1;
				return leftLinkIndex;
			} else {
				int promotedIndex = min(rightLinks[index]);
				rightLinks[promotedIndex] = deleteMin(rightLinks[index], false);
				leftLinks[promotedIndex] = leftLinks[index];
				rightLinks[index] = -1;
				leftLinks[index] = -1;
				index = promotedIndex;
			}
		}
		size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
		return index;
	}

	public void deleteMin() {
		int rootIndex = deleteMin(0, true);
	}
	private int deleteMin(int index, boolean setKeyNull) {
		if (index == -1 || keys[index] == null) {
			return -1;
		}

		int leftIndex = deleteMin(leftLinks[index], setKeyNull);
		leftLinks[index] = leftIndex;

		size[index] = size(leftLinks[index]) + 1 + size(rightLinks[index]);
		return index;
	}
}

class BSTArray {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of Entries");
		int n = scan.nextInt();
		BSTArrayRepresentation<Integer, String> arrayRepresentation = new BSTArrayRepresentation<>(n);
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
