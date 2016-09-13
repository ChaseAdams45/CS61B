public class LinkedListDeque<Item> {

    public class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item i) {
            item = i;
            next = null;
            prev = null;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }

    /*
    Adds an item to the front of the Deque.
     */
    public void addFirst(Item i) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node first = new Node(item);
        first.prev = sentinel;
        first.next = sentinel.next;

        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    /*
    Adds an item to the back of the Deque.
     */
    public void addLast(Item i) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        Node last = new Node(item);
        last.prev = sentinel.prev;
        last.next = sentinel;

        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    /*
    Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
    Returns the number of items in the Deque.
     */
    public int size() {
        return size;
    }

    /*
    Prints the items in the Deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.get(i) + " ");
        }
    }

    /*
    Removes and returns the item at the front of the Deque.
    If no such item exists, returns null.
     */
    public Item removeFirst() {
        Node oldfirst = sentinel.next;
        Item olditem = oldfirst.item;
        sentinel.next = oldfirst.next;
        oldfirst.next.prev = sentinel;
        size--;
        oldfirst = null;
        return olditem;
    }

    /*
    Removes and returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    public Item removeLast() {
        Node oldlast = sentinel.prev;
        Item olditem = oldlast.item;
        sentinel.prev = oldlast.prev;
        oldlast.prev.next = sentinel;
        size--;
        oldlast = null;
        return olditem;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next
    item, and so forth. If no such item exists, returns null.
    Must not alter the deque!
     */
    public Item get(int index) {

        if (size == 0 || index > size) return null;

        Node old = sentinel.next;
        for (int i = 0; i < index; i++) {
            old = old.next;
        }
        return old.item;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null.
    Must not alter the deque!
     */
    public Item getRecursive(int index) {
        return null;
    }

}
