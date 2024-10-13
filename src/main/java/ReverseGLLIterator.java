import java.util.Iterator;
import java.util.Stack;

public class ReverseGLLIterator<E> implements Iterator<E> {
    private Stack<E> stack;


    // constructor
    // passes in the head of the list to be iterated through
    ReverseGLLIterator(GenericList.Node<E> head) {
        this.stack = new Stack<E>();
        GenericList.Node<E> currNode = head;

        while (currNode != null) {
            this.stack.push(currNode.data);
            currNode = currNode.next;
        }
    }

    // checks if there is another value in the data structure
    // returns true if there is; false otherwise
    public boolean hasNext() {
        if (this.stack.size() > 0) {
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
        return this.stack.pop();
    }
}
