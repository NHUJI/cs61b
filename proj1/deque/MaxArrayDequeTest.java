package deque;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Comparator;

/**
 * satanaileo
 * 2022/2/15 17:21
 */
public class MaxArrayDequeTest {
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class SizeComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    private static class HeadComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.charAt(0) - o2.charAt(0);
        }
    }

    @Test
    public void testIntMax() {
        MaxArrayDeque<Integer> maxArray = new MaxArrayDeque<>(new IntComparator());
        for (int i = 0; i < 10; i += 1) {
            maxArray.addFirst(i);
        }
        assertEquals(9, (long) maxArray.max());
    }

    @Test
    public void testStringMax() {
        MaxArrayDeque<String> maxArray = new MaxArrayDeque<>(new SizeComparator());
        maxArray.addFirst("Alice");
        maxArray.addFirst("Bob");
        maxArray.addFirst("Cu");
        assertEquals("Alice", maxArray.max());
        assertEquals("Cu", maxArray.max(new HeadComparator()));
    }

    @Test
    public void testFromAuto() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(new IntComparator());
        maxArrayDeque.addFirst(0);
        maxArrayDeque.addLast(1);
        maxArrayDeque.removeLast();
        maxArrayDeque.addLast(3);
        maxArrayDeque.addFirst(4);
        maxArrayDeque.addFirst(5);
        maxArrayDeque.addLast(6);
        maxArrayDeque.addFirst(7);
        maxArrayDeque.addFirst(8);
        maxArrayDeque.addFirst(9);
        maxArrayDeque.addFirst(10);
        maxArrayDeque.removeLast();
        maxArrayDeque.removeFirst();
        maxArrayDeque.removeLast();
        maxArrayDeque.removeLast();
        maxArrayDeque.removeFirst();
        maxArrayDeque.addLast(16);
        maxArrayDeque.removeFirst();
        maxArrayDeque.removeFirst();
        maxArrayDeque.removeLast();
    }
}