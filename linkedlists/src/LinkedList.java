import java.util.ListIterator;
import java.util.NoSuchElementException;




public class LinkedList{
	
    private Node head;
    private int size;
    private Node tail;

    private class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
        }
    }

    public LinkedList() {
        this.head = null;
        this.size = 0;
        this.tail = null;
        
    }

    public void add(String element) {
        Node newNode = new Node(element);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, String element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void set(int in, String string) {
        Node current = head;
        for (int i = 0; i < in; i++) {
            current = current.next;
        }
        current.data = string;
    }
    
    public ListIterator listIterator() {
        return new ListIterator();
    }

    private class ListIterator implements java.util.ListIterator<String> {
        private Node current;
        private int index;

        public ListIterator() {
            current = head;
            index = 0;
        }

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String data = current.data;
            current = current.next;
            index++;
            return data;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        public String previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            index--;
            return current.data;
        }
        

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(String e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(String e) {
			// TODO Auto-generated method stub
			
		}
    }

	
}