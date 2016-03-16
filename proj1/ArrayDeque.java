public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private static int RFACTOR = 2;



    public ArrayDeque() {
        size = 0;
        items = (Item[]) new Object[8];

    }

    private void resize(int capacity) {

        Item[] fresh = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, fresh, 0, size);
        items = fresh;
    }

    /*
    Adds an item to the front of the Deque.
     */
    public void addFirst(Item i) {

        items[0] = i;
        size++;
    }

    /*
    Adds an item to the back of the Deque.
     */
    public void addLast(Item i) {

        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[size] = i;;
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

        for (int i = 0; i < this.size(); i++) {
            System.out.print(this.get(i) + " ");
        }
    }

    /*
    Removes and returns the item at the front of the Deque.
    If no such item exists, returns null.
     */
    public Item removeFirst() {

        return null;
    }

    /*
    Returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    private Item getBack() {

        return items[size - 1];
    }

    /*
    Removes and returns the item at the back of the Deque.
    If no such item exists, returns null.
     */
    public Item removeLast() {

        Item last = getBack();
        items[size - 1] = null;
        size--;
        return last;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item,
    and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public Item get(int index) {

        if (index > size) return null;

        return items[index];
    }




}
