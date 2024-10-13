import java.util.Iterator;

public class GLLIterator<E> implements Iterator<E> {

    private GenericList.Node<E> currNode;

    // constructor
    // passes in the head of the list to be iterated through
    GLLIterator(GenericList.Node<E> head) {
        this.currNode = head;
    }

    // checks if there is another value in the data structure
    // returns true if there is; false otherwise
    public boolean hasNext() {
        if (this.currNode != null) {
            return true;
        }
        return false;
    }


    // returns the current value in the data structure and advances to the next node
    // returns null if the value does not exist
    public E next() {
        if (!this.hasNext()) {
            return null;
        }

        E prevValue = this.currNode.data;
        this.currNode = this.currNode.next;

        return prevValue;
    }
}
