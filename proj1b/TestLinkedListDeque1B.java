import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;

public class TestLinkedListDeque1B {
    @Test
    public void testIsEmpty() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        assertTrue(test.isEmpty());
        test.addFirst(1);
        assertFalse(test.isEmpty());
    }

    @Test
    public void testSize() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        assertEquals(0, test.size());
        test.addFirst(1);
        assertEquals(1, test.size());
    }

    @Test
    public void testRemoveFirst() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> correct = new LinkedListDequeSolution<Integer>();

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
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> correct = new LinkedListDequeSolution<Integer>();

        // removeLast() should return null if empty
        assertEquals(correct.removeLast(), test.removeLast());

        test.addFirst(1);
        correct.addFirst(1);
        assertEquals(correct.removeLast(), test.removeLast());

        // removeLast() should return null if empty
        assertEquals(correct.removeLast(), test.removeLast());
    }

    @Test
    public void testGet() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> correct = new LinkedListDequeSolution<Integer>();
        test.addFirst(1);
        correct.addFirst(1);
        assertEquals(correct.get(0), test.get(0));
    }

    @Test
    public void testLinkedListDeque() {
        assertEquals(new StudentLinkedListDeque<Integer>().size(), new LinkedListDequeSolution<Integer>().size());
    }

    @Test
    public void testGetRecursive() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> correct = new LinkedListDequeSolution<Integer>();
        test.addFirst(1);
        correct.addFirst(1);
        assertEquals(correct.get(0), test.get(0));
    }

    @Test
    public void testBug() {
        StudentLinkedListDeque<Integer> test = new StudentLinkedListDeque<Integer>();
        LinkedListDequeSolution<Integer> correct = new LinkedListDequeSolution<Integer>();

        FailureSequence fs = new FailureSequence();

        for (int i = 0; i < 6; i++) {
            DequeOperation dequeOp1 = new DequeOperation("addFirst", i);
            test.addFirst(i);
            correct.addFirst(i);
            fs.addOperation(dequeOp1);

            if (i % 2 == 0) {
                DequeOperation dequeOp2 = new DequeOperation("removeLast");
                fs.addOperation(dequeOp2);
                String message = "\n" + fs.toString();
                assertEquals(message, correct.removeLast(), test.removeLast());
            }
        }
    }
}
