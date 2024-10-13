public class GenericQueue<T> extends GenericList<T> {

    // data member
    private Node<T> tail;

    // constructors

    // only data
    public GenericQueue(T data) {
        Node<T> initialNode = new Node<>(data);
        this.setHead(initialNode);
        this.setTail(initialNode);
        this.setLength(1);
    }

    // data + code
    // intended for use in 'MyHashMap'
    public GenericQueue(T data, int code) {
        Node<T> initialNode = new Node<>(data);
        initialNode.code = code;
        this.setHead(initialNode);
        this.setTail(initialNode);
        this.setLength(1);
    }


    // getter and setter for tail
    // meant to keep syntax for accessing data members consistent

    // returns 'tail' data member
    public Node<T> getTail() {
        return this.tail;
    }

    // sets 'tail' data member
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }


    // methods

    // adds a new node to the end of the list
    public void add(T data) {
        Node<T> nodeToAdd = new Node<>(data);
        if (this.getHead() == null) { // check if list is empty
            this.setHead(nodeToAdd);
            this.setTail(nodeToAdd);
        }
        else {
            this.getTail().next = nodeToAdd;
            this.setTail(nodeToAdd);
        }
        this.setLength(this.getLength() + 1);
    }
    // same purpose as add(T data), but
    // also sets code to data
    public void add(T data, int code) {
        this.add(data);
        this.getTail().code = code; // sets tail's code
    }


    // deletes that first node in the list
    // returns the value of the deleted node
    public T delete() {
        if (this.getLength() == 0) {
            return null; // empty list
        }

        // if the tail is getting deleted, then it will be set to null
        if (this.getLength() == 1) {
            this.setTail(null);
        }

        // deletes the head and decrements size by 1
        Node<T> oldHead = this.getHead();
        T data = oldHead.data; // saves return value
        this.setHead(oldHead.next);
        oldHead = null;

        this.setLength(this.getLength() - 1);

        return data;
    }


    // calls add()
    // implemented for user ease-of-use
    public void enqueue(T data) {
        this.add(data);
    }


    // calls delete()
    // implemented for user ease-of-use
    public T dequeue() {
        return this.delete();
    }


    // returns the data from an element in the queue with a specified code
    // returns null if element does not exist
    public T searchForCode(int code) {
        Node<T> currNode = this.getHead();
        while (currNode != null) {
            if (currNode.code == code) {
                return currNode.data;
            }
            currNode = currNode.next;
        }
        return null;
    }


    // replaces the value at a node with the parameter 'code' with 'newData'
    // returns the value previously stored in the node
    public T replaceAtCode(T newData, int code) {
        Node<T> currNode = this.getHead();
        while (currNode != null) {
            if (currNode.code == code) {
                T oldData = currNode.data;
                currNode.data = newData;
                return oldData;
            }
            currNode = currNode.next;
        }
        return null;
    }
}