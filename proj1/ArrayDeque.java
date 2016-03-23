public class ArrayDeque<Item> {

    private Item[] items;
    private int size;
    private int pointerFirst;
    private int pointerLast;
    private static int RFACTOR = 2;


    public ArrayDeque() {

        size = 0;
        pointerFirst = 3;
        pointerLast = 4;
        items = (Item[]) new Object[8];
    }

    private int minusOne(int index) {

        if (index == 0) {return items.length;}
        return index - 1;
    }

    private void resize(int capacity) {

        Item[] fresh = (Item[]) new Object[capacity];

        for (int i = 0; i <= pointerFirst; i++) {
            fresh[i] = items[i];
        }

        for (int i = pointerLast; i < size; i++) {
            fresh[i + size] = items[i];
        }

        pointerFirst += size;
        items = fresh;
    }

    /*
    Adds an item to the front of the Deque.
     */
    public void addFirst(Item i) {

        if (size == items.length) { resize(size * RFACTOR); }

        items[pointerFirst] = i;
        pointerFirst--;
        if (pointerFirst < 0) { pointerFirst = items.length - 1;}
        size++;
    }

    /*
    Adds an item to the back of the Deque.
     */
    public void addLast(Item i) {

        if (size == items.length) { resize(size * RFACTOR); }

        items[pointerLast] = i;
        pointerLast++;
        if (pointerLast == items.length) { pointerLast = 0;}
        size++;
    }

    /*
    Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {

        return size==0;
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
        for (int i = 0; i < items.length; i++) {
            System.out.print(this.get(i) + " ");
        }

    }

    /*
    Removes and returns the item at the front of the Deque.
    If no such item exists, returns null.
     */
    public Item removeFirst() {

        Item removed = items[pointerFirst + 1];
        items[pointerFirst + 1] = null;
        pointerFirst++;
        size--;
        return removed;
    }

    /*
    Removes and returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    public Item removeLast() {

        Item removed = items[pointerLast - 1];
        items[pointerLast - 1] = null;
        pointerLast--;
        size--;
        return removed;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public Item get(int index) {

        return items[index];
    }
}
