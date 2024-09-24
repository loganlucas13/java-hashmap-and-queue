import java.util.Iterator;
import java.util.ArrayList;


public abstract class GenericList<T> implements Iterable<T> {

    public class Node<T> {
        T data;
        int code; // optional field
        Node<T> next;
    }

    // data members
    private Node<T> head;
    private int length;


    // basic setters and getters

    // returns 'length' data member
    public int getLength() {
        return this.length;
    }


    // sets 'length' data member
    public void setLength(int length) {
        this.length = length;
    }


    // returns 'head' data member
    public Node<T> getHead() {
        return this.head;
    }


    // sets 'head' data member
    public void setHead(Node<T> head) {
        this.head = head;
    }


    // methods

    // prints items of the list, one value per line
    // if list is empty, prints "Empty List"
    public void print() {
        if (this.length == 0) {
            System.out.println("Empty List");
            return;
        }

        Node<T> currNode = this.head;
        while (currNode != null) {
            System.out.println(currNode.data);
            currNode = currNode.next;
        }
    }


    // adds the parameter 'data' to the list
    public abstract void add(T data);


    // deletes nodes from the data structure
    // if list is empty, returns null
    public abstract T delete();


    // stores all values currently in the list into an ArrayList and returns
    public ArrayList<T> dumpList() {
        ArrayList<T> result = new ArrayList<>();

        Node<T> currNode = this.head;
        while (currNode != null) {
            result.add(currNode.data);
            currNode = currNode.next;
        }
        return result;
    }


    // returns the value at the specified index
    // returns null if index is out of bounds
    public T get(int index) {
        if (index < 0 || index >= this.length) {
            return null;
        }

        Node<T> currNode = this.head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.data;
    }

    // replaces the element at the specified index with the 'element' parameter
    // returns the element that was replaced OR null if index is out of bounds
    public T set(int index, T element) {
        if (index < 0 || index >= this.length) {
            return null;
        }

        Node<T> currNode = this.head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        T previousData = currNode.data;
        currNode.data = element;

        return previousData;
    }


    // returns an iterator from tail to head over the list
    public Iterator<T> descendingIterator() {
        // TODO: implement after finishing 'ReverseGLLIterator.java'
        return null;
    }
}
