public class GenericQueue<T> extends GenericList<T> {

    // data member
    Node<T> tail;

    // constructor
    public GenericQueue(T data) {
        Node<T> initialNode = new Node<>(data);
        this.setHead(initialNode);
        this.setLength(1);
        this.tail = initialNode;
    }


    // methods

    // adds a new node to the end of the list
    public void add(T data) {
        Node<T> newTail = new Node<>(data);
        this.tail.next = newTail;
        this.tail = newTail;
        this.setLength(this.getLength() + 1);
    }
    // same purpose as add(T data), but
    // also sets code to data
    public void add(T data, int code) {
        this.add(data);
        this.tail.code = code; // sets tail's code
    }


    // deletes that last node in the list
    // returns the value of the deleted node
    public T delete() {
        if (this.getLength() == 0) {
            return null; // empty list
        }

        T data; // saves return value

        // head == tail == node to delete
        if (this.getLength() == 1) {
            data = this.getHead().data;
            this.setHead(null);
            this.tail = null;
            this.setLength(0);
            return data;
        }

        Node<T> currNode = this.getHead();
        while (currNode.next != tail) {
            currNode = currNode.next;
        }
        data = currNode.next.data;
        currNode.next = null;
        this.tail = currNode;
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
}
