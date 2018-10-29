/**
 * { Program for Bags }.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * { number of elements in bag }.
     */
    private int N;
    /**
     * { beginning of bag }.
     */
    private Node first;
    /**
     * Class for node.
     * helper linked list class
     */
    private class Node {
        /**
         * { variable for item }.
         */
        private Item item;
        /**
         * { variable for next node }.
         */
        private Node next;
    }
    /**
     * Constructs the object.
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        N = 0;
    }

    /**
     * Determines if empty.
     * Is the BAG empty?
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * { Return the number of items in the bag }.
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return N;
    }

    /**
     * { Add the item to the bag }.
     *
     * @param      item  The item
     */
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    /**
     * { Return an iterator that iterates over the items in the bag }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
