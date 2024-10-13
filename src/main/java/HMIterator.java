import java.util.Iterator;
import java.util.ArrayList;

public class HMIterator<E> implements Iterator<E> {

    private ArrayList<GenericQueue<E>> map;
    private int currIndex;
    private Iterator<E> currQueueIterator;

    // constructor
    HMIterator(ArrayList<GenericQueue<E>> map) {
        this.map = map;
        this.currIndex = 0;
        while (this.map.get(currIndex) == null && this.currIndex < this.map.size()) {
            this.currIndex++;
        }

        if (this.map.get(currIndex) != null) {
            this.currQueueIterator = this.map.get(currIndex).iterator();
        }
        else {
            this.currQueueIterator = null;
        }
    }


    // checks if there is another value in the data structure
    // returns true if there is; false otherwise
    public boolean hasNext() {
        if (this.currQueueIterator == null) {
            return false;
        }

        while (this.currIndex < this.map.size()) {
            if (this.currQueueIterator.hasNext()) {
                return true;
            }
            this.currIndex++;
            if (this.currIndex < this.map.size() && this.map.get(this.currIndex) != null) {
                this.currQueueIterator = this.map.get(this.currIndex).iterator();
            }
        }
        return false;
    }


    // returns the current value in the data structure and advances to the next node
    // returns null if the value does not exist
    public E next() {
        if (!this.hasNext()) {
            return null;
        }
        return this.currQueueIterator.next();
    }
}
