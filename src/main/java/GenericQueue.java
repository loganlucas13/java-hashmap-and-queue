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
}
