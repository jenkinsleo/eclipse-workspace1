//2019-03-16
import junit.framework.TestCase;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedListIteratorTest extends TestCase {
	
    public void testPreviousNext(){

    	LinkedList list = new LinkedList();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
               
        ListIterator i = list.listIterator();

        assertEquals("zero",  i.next());

        assertEquals(true, i.hasNext());
        assertEquals(1, i.nextIndex());        
        assertEquals("one",  i.next());
        
        assertEquals(true, i.hasNext());
        assertEquals(2, i.nextIndex());
        assertEquals("two",  i.next());
        
        assertEquals(true, i.hasNext());
        assertEquals(3, i.nextIndex());
        assertEquals("three",  i.next());
        
        assertEquals(true, i.hasNext());
        assertEquals(4, i.nextIndex());
        assertEquals("four",  i.next());
        
        assertEquals(false, i.hasNext());
        assertEquals(5, i.nextIndex());
        
        //this next block checks if the iterator throws an exception when it has reached
        //the end of the list and the .next() method is invoked
        boolean noSuchElementThrown = false;
        try {
			i.next();
		}
        catch (NoSuchElementException e) {
			noSuchElementThrown = true;
		}
        finally {
            assertEquals(true,  noSuchElementThrown);
        }
        
        //cursor should now be past 'four', so .previous() should return 'four'
        assertEquals("four",  i.previous());
        
        assertEquals(true, i.hasPrevious());
        assertEquals(3, i.previousIndex());        
        assertEquals("three",  i.previous());
        
        assertEquals(true, i.hasPrevious());
        assertEquals(2, i.previousIndex());        
        assertEquals("two",  i.previous());

        assertEquals(true, i.hasPrevious());
        assertEquals(1, i.previousIndex());        
        assertEquals("one",  i.previous());

        assertEquals(true, i.hasPrevious());
        assertEquals(0, i.previousIndex());        
        assertEquals("zero",  i.previous());
        
        //this next block checks if the iterator throws an exception when it has reached
        //the beginning of the list and the .previous() method is invoked
        noSuchElementThrown = false;
        try {
			i.previous();
		}
        catch (NoSuchElementException e) {
			noSuchElementThrown = true;
		}
        finally {
            assertEquals(true,  noSuchElementThrown);
        }
        
    }
    
    public void testSet() {
    	
        LinkedList list = new LinkedList();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        
        ListIterator i = list.listIterator();
        
        //move three elements to the right
        assertEquals("zero",  i.next());
        assertEquals("one",  i.next());
        assertEquals("two",  i.next());

        //set should replace the last element returned by next() or previous()
        i.set("TWO");
        assertEquals("TWO", list.get(2));        
        
       //cursor should be between 2 and 3; a previous() should return value at 2
        assertEquals("TWO",  i.previous());
        //cursor should be between 1 and 2; a previous() should return value at 1
        assertEquals("one",  i.previous());
        
        i.set("ONE");
        //set should replace the last element returned by next() or previous()
        assertEquals("ONE", list.get(1));        
        
        //cursor should now be between 0 and 1

        i.next();
        //cursor should now be between 1 and 2

        i.next();
        //cursor should now be between 2 and 3

        i.next();
        //cursor should now be between 3and 4

        //set should replace the last element returned by next() or previous()
        i.set("THREE");
        i.next();
        //set should replace the last element returned by next() or previous()
        i.set("FOUR");
        assertEquals("[zero, ONE, TWO, THREE, FOUR]", list.toString());
        
    	
    }

    public void testAdd() {
    	
        LinkedList list = new LinkedList();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        
        ListIterator<String> i = list.listIterator();
        
        //move two elements to the right
        assertEquals("zero",  i.next());
        assertEquals("one",  i.next());

        //insert new value at current cursor, which should be between one and two
        //the new element is inserted before the implicit cursor: 
        i.add("root two");
        assertEquals("[zero, one, root two, two, three, four]", list.toString());

        //move two to the right; should be unaffected by add
        assertEquals("two",  i.next());
        assertEquals("three",  i.next());
        
        //insert new value at current cursor, which should be between three and four
        //the new element is inserted before the implicit cursor: 
        i.add("PI");
        assertEquals("[zero, one, root two, two, three, PI, four]", list.toString());

        //subsequent call to previous would return the new element
        assertEquals("PI",  i.previous());

        assertEquals("three",  i.previous());

        i.add("e");
        assertEquals("[zero, one, root two, two, e, three, PI, four]", list.toString());
        //this call should not move the iterator, but will check if the iterator is in the correct place, between e and three
        assertEquals("three", list.get(i.nextIndex()));

        //this next block checks if the iterator throws an IllegalState exception when an add is called
        //when neither next nor previous have been called, or remove or add have been called after the last call to next or previous
        boolean illegalStateThrown = false;
        try {
            i.set("THREE");
		}
        catch (IllegalStateException e) {
			illegalStateThrown = true;
		}
        finally {
            assertEquals(true,  illegalStateThrown);
        }
        
        
    }

    public void testRemove() {
    	
        LinkedList list = new LinkedList();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        
        ListIterator i = list.listIterator();
    
        //move three elements to the right
        assertEquals("zero",  i.next());
        assertEquals("one",  i.next());
        assertEquals("two",  i.next());
        
        i.remove();
        assertEquals("[zero, one, three, four]", list.toString());
        //these next calls should not move the iterator, but will check if the iterator is in the correct place, between one and three
        assertEquals("one", list.get(i.previousIndex()));
        assertEquals("three", list.get(i.nextIndex()));
        
        //move three elements to the right
        assertEquals("three",  i.next());
        i.remove();
        assertEquals("[zero, one, four]", list.toString());
        
        //this next block checks if the iterator throws an IllegalState exception when a remove is called
        //when neither next nor previous have been called, or remove or add have been called after the last call to next or previous
        boolean illegalStateThrown = false;
        try {
            i.remove();
		}
        catch (IllegalStateException e) {
			illegalStateThrown = true;
		}
        finally {
            assertEquals(true,  illegalStateThrown);        
        }
        
    }
    
}
