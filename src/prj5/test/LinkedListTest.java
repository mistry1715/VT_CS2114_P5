package prj5.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import prj5.*;

/**
 * Test the LinkedList class.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class LinkedListTest extends student.TestCase {
    private LinkedList<String> list;
    private Iterator<String> iterator;

    /**
     * sets up for testing
     */
    public void setUp() {
        list = new LinkedList<String>();
    }

    /**
     * tests the add method that adds to the end of the list
     */
    public void testAdd() {
        list.add("h");
        list.add("i");
        assertTrue(list.get(0).equals("h"));
        assertTrue(list.get(1).equals("i"));
        list.add("h");
        assertTrue(list.get(1).equals("i"));
    }

    /**
     * tests the add method with index as parameter
     */
    public void testAddWithIndex() {
        list.add(0, "o");
        list.add(0, "r");
        list.add(2, "e");
        list.add(2, "b");
        assertTrue(list.get(0).equals("r"));
        assertTrue(list.get(1).equals("o"));
        assertTrue(list.get(2).equals("b"));
        assertTrue(list.get(3).equals("e"));

        Exception thrown = null;
        try {
            list.add(12, "exception");
        }
        catch (Exception e) {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IndexOutOfBoundsException);
    }

    /**
     * tests the get function
     */
    public void testGet() {
        list.add("h");
        assertTrue(list.get(0).equals("h"));
        list.add("e");
        list.remove("h");
        assertTrue(list.get(0).equals("e"));

        Exception thrown = null;

        try {
            list.get(-1);
        }
        catch (Exception e) {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IndexOutOfBoundsException);
    }

    /**
     * tests the isEmpty function
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("h");
        assertFalse(list.isEmpty());
    }

    /**
     * tests the size function
     */
    public void testSize() {
        assertEquals(list.size(), 0);
        list.add("h");
        list.add("e");
        list.add(2, "l");
        list.add(0, "h");
        assertEquals(list.size(), 4);
        assertTrue(list.remove(2).equals("e"));
        assertEquals(list.size(), 3);
    }

    /**
     * tests the contains method
     */
    public void testContains() {
        list.add("h");
        list.add("e");
        assertTrue(list.contains("e"));
        assertFalse(list.contains("o"));
        list.add("l");
        assertTrue(list.contains("l"));
    }

    /**
     * tests the remove method with an integer as a parameter
     */
    public void testRemoveIndex() {
        list.add("h");
        list.add("e");
        list.add("l");
        list.add("l");
        assertTrue(list.remove(1).equals("e"));
        assertTrue(list.remove(0).equals("h"));
        list.add("o");
        assertTrue(list.remove(1).equals("l"));
        assertTrue(list.remove(1).equals("o"));
    }

    /**
     * tests the remove method with entry parameter
     */
    public void testRemoveEntry() {
        list.add("h");
        list.add("e");
        list.add("l");
        list.add("l");
        list.add("o");
        assertTrue(list.remove("e").equals("e"));
        assertTrue(list.remove("o").equals("o"));
        assertTrue(list.remove("h").equals("h"));
    }

    /**
     * tests the clear method
     */
    public void testClear() {
        list.add("h");
        list.clear();
        assertTrue(list.isEmpty());
    }

    /**
     * tests the iterator
     */
    public void testIterator() {
        list.add("h");
        list.add("e");
        list.add("l");
        list.add("l");
        list.add("o");
        iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.next().equals("h"));
        assertTrue(iterator.next().equals("e"));

        Exception thrown = null;
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(list.size(), 3);

        try {
            iterator.remove();
        }
        catch (Exception e) {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalStateException);

        iterator.next();
        assertTrue(iterator.next().equals("o"));

        thrown = null;
        try {
            iterator.next();
        }
        catch (Exception e) {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);

        iterator = list.iterator();
        assertTrue(iterator.next().equals("h"));
        assertTrue(iterator.next().equals("l"));
        iterator.remove();
        assertEquals(list.size(), 2);
        assertTrue(iterator.next().equals("o"));
    }
}