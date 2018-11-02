/**
 * Class for linear probing hash st.
 *
 * @param      <Key>    The key
 * @param      <Value>  The value
 */
class HashTable<Key, Value> {
    /**
     * { variable for capacity }.
     */
    private static final int INIT_CAPACITY = 4;
    /**
     * { number of key-value pairs in the symbol table }.
     */
    private int n;
    /**
     * { size of linear probing table }.
     */
    private int m;
    /**
     * { variable for the keys }.
     */
    private Key[] keys;
    /**
     * { variable for the values }.
     */
    private Value[] vals;
    /**
     * Constructs the object.
     * Initializes an empty symbol table.
     */
    HashTable() {
        this(INIT_CAPACITY);
    }

    /**
     * Constructs the object.
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param      capacity  The capacity
     */
    HashTable(final int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

    /**
     * { Returns the number of key-value pairs in this symbol table }.
     *
     * @return     { the number of key-value pairs in this symbol table }
     */
    public int size() {
        return n;
    }

    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * { Returns true if this symbol table contains the specified key }.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException(
                "argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * { hash function for keys - returns value between 0 and M-1 }.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    private int hash(final Key key) {
        final int eleven = 11;
        return (key.hashCode() * eleven) % m;
    }

    /**
     * { resizes the hash table to the given capacity
     *   by re-hashing all of the keys }.
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        HashTable<Key, Value> temp =
            new HashTable<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }

    /**
     * { Inserts the specified key-value pair into the symbol table,
     *   overwriting the old value with the new value if the symbol table
     *   already contains the specified key.Deletes the specified key
     *   (and its associated value) from this symbol table if the
     *   specified value is null }.
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final Key key, final Value val) {
        if (key == null) {
            throw new IllegalArgumentException(
                "first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        // double table size if 50% full
        if (n >= m / 2) {
            resize(2 * m);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * { Returns the value associated with the specified key }.
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public Value get(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * { Removes the specified key and its
     *   associated value from this symbol table }.
     *
     * @param      key   The key
     */
    public void delete(final Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;
        final int eight = 8;
        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m / eight) {
            resize(m / 2);
        }

        assert check();
    }

    // /**
    //  * Returns all keys in this symbol table as an {@code Iterable}.
    //  * To iterate over all of the keys in the symbol table named {@code st},
    //  * use the foreach notation: {@code for (Key key : st.keys())}.
    //  *
    //  * @return all keys in this symbol table
    //  */
    // public Iterable<Key> keys() {
    //     Queue<Key> queue = new Queue<Key>();
    //     for (int i = 0; i < m; i++)
    //         if (keys[i] != null) queue.enqueue(keys[i]);
    //     return queue;
    // }

    /**
     * { function for integrity check }.
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check() {

        // check that hash table is at most 50% full
        if (m < 2 * n) {
            System.err.println("Hash table size m = "
                               + m + "; array size n = " + n);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) {
                continue;
            } else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = "
                                   + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }

    /**
     * { function for display }.
     */
    public void display() {
        if (size() == 0) {
            System.out.println("{}");
            return;
        }
        String str = "{";
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                str += keys[i] + ":" + vals[i] + ", ";
            }
        }
        str = str.substring(0, str.length() - 2);
        str = str + "}";
        System.out.println(str);
    }

    // /**
    //  * Unit tests the {@code HashTable} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     LinearProbingHashST<String, Integer> st =
    //     new LinearProbingHashST<String, Integer>();
    //     for (int i = 0; !StdIn.isEmpty(); i++) {
    //         String key = StdIn.readString();
    //         st.put(key, i);
    //     }

    //     // print keys
    //     for (String s : st.keys())
    //         StdOut.println(s + " " + st.get(s));
    // }
}
