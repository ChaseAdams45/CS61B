public class LinkedListDeque<Item> {

    public class Node {
        public Item item;
        public Node next;
        public Node prev;

        public Node(Item i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, sentinel, sentinel);

    }

    /*
    Adds an item to the front of the Deque.
     */
    public void addFirst(Item i) {

        if (size == 0) {
            sentinel.next = new Node(i, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size++;
        } else {
            Node oldnext = sentinel.next;
            sentinel.next = new Node(i, sentinel, oldnext);
            oldnext.prev = sentinel.next;
            size++;
        }
    }

    /*
    Adds an item to the back of the Deque.
     */
    public void addLast(Item i) {

        if (size == 0) {
            sentinel.next = new Node(i, sentinel, sentinel);
            sentinel.prev = sentinel.next;
            size++;
        } else {
            Node oldprev = sentinel.prev;
            sentinel.prev = new Node(i, oldprev, sentinel);
            oldprev.next = sentinel.prev;
            size++;
        }
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
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return oldfirst.item;
    }

    /*
    Removes and returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    public Item removeLast() {

        Node oldlast = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return oldlast.item;
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
