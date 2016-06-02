import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDeque1B {

    @Test
    public void testIsEmpty() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        assertTrue(test.isEmpty());
        test.addFirst(1);
        assertFalse(test.isEmpty());
    }

    @Test
    public void testSize() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        assertEquals(0, test.size());
        test.addFirst(1);
        assertEquals(1, test.size());
    }

    @Test
    public void testRemoveFirst() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<Integer>();

        // should return null if empty
        assertEquals(correct.removeFirst(), test.removeFirst());

        test.addFirst(1);
        correct.addFirst(1);
        assertEquals(correct.removeFirst(), test.removeFirst());

        // should return null if empty
        assertEquals(correct.removeFirst(), test.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<Integer>();

        FailureSequence fs = new FailureSequence();

        // removeLast() should return null if empty
        assertEquals(correct.removeLast(), test.removeLast());

        // Bug #1.
        test.addFirst(1);
        correct.addFirst(1);
        DequeOperation dequeOp1 = new DequeOperation("addFirst", 1);
        fs.addOperation(dequeOp1);

        DequeOperation dequeOp2 = new DequeOperation("removeLast");
        fs.addOperation(dequeOp2);
        String message = "\n" + fs.toString();

        assertEquals(message, correct.removeLast(), test.removeLast());
    }

    @Test
    public void testGet() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<Integer>();
        test.addFirst(1);
        correct.addFirst(1);
        assertEquals(correct.get(0), test.get(0));
    }

    @Test
    public void  testSizeBug() {
        StudentArrayDeque<Integer> test = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<Integer>();

        FailureSequence fs = new FailureSequence();

        // Bug #2.
        assertEquals(correct.removeFirst(), test.removeFirst());
        DequeOperation dequeOp1 = new DequeOperation("removeFirst");
        fs.addOperation(dequeOp1);

        DequeOperation dequeOp2 = new DequeOperation("size");
        fs.addOperation(dequeOp2);
        String message = "\n" + fs.toString();
        assertEquals(message, correct.size(), test.size());
    }
}
