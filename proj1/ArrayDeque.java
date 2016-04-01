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

    private void sizeDecrease(int capacity) {

        Item[] fresh = (Item[]) new Object[capacity];

        if (pointerFirst > pointerLast) {

            for (int i = 0; i <= pointerLast; i++) {
                fresh[i] = items[i];
            }

            for (int i = pointerFirst + 1; i < items.length; i++) {
                fresh[i - capacity] = items[i];
            }

            pointerFirst -= capacity;
            items = fresh;

        } else {

            int newIndex = 1;
            for (int i = pointerFirst+ 1; i < pointerLast; i++) {
                fresh[newIndex] = items[i];
                newIndex++;
            }

            pointerFirst = 0;
            pointerLast = newIndex;
            items = fresh;
        }
    }

    private void sizeIncrease(int capacity) {

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

        if (size == items.length) {
            sizeIncrease(size * RFACTOR);
        }

        items[pointerFirst] = i;
        pointerFirst--;

        if (pointerFirst < 0) {
            pointerFirst = items.length - 1;
        }

        size++;
    }

    /*
    Adds an item to the back of the Deque.
     */
    public void addLast(Item i) {

        if (size == items.length) {
            sizeIncrease(size * RFACTOR);
        }

        items[pointerLast] = i;
        pointerLast++;

        if (pointerLast == items.length) {
            pointerLast = 0;
        }

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

        if (pointerFirst == items.length - 1) {
            pointerFirst = -1;
        }

        Item removed = items[pointerFirst + 1];
        items[pointerFirst + 1] = null;
        pointerFirst++;
        size--;

        if (needDecrease()) {
            sizeDecrease(items.length / RFACTOR);
        }

        return removed;
    }

    /*
    Removes and returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    public Item removeLast() {

        if (pointerLast == 0) {
            pointerLast = items.length;
        }

        Item removed = items[pointerLast - 1];
        items[pointerLast - 1] = null;
        pointerLast--;
        size--;

        if (needDecrease()) {
            sizeDecrease(items.length / RFACTOR);
        }

        return removed;
    }

    private boolean needDecrease() {

        if (items.length >= 16) {
            return (items.length / size) >= 4;
        }
        return false;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public Item get(int index) {

        return items[index];
    }
}

