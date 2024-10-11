import java.util.Iterator;
import java.util.ArrayList;

public class MyHashMap<T> implements Iterable<T> {

    // data members
    private ArrayList<GenericQueue<T>> map;
    private int length;


    // constructor
    MyHashMap(String key, T value) {
        this.map = new ArrayList<GenericQueue<T>>(10);
        this.put(key, value);
        this.setLength(1);
    }


    // getter and setter for length
    // for consistent syntax on accessing/modifying data members

    // returns 'length' data member
    public int getLength() {
        return this.length;
    }

    // sets 'length' data member
    public void setLength(int length) {
        this.length = length;
    }


    // methods

    // 1. creates a hash code + value from the key
    // 2. checks if there is a GenericQueue at the index in the map
    //   -> if not: creates a new GenericQueue and adds it to the map at the index of the hash value
    //   -> otherwise: appends a new node to the existing GenericQueue at the index
    public void put(String key, T value) {
        int index = key.hashCode()&9;
        if (this.map.get(index) == null) {
            this.map.set(index, new GenericQueue<>(value));
        }
        else {
            this.map.get(index).enqueue(value);
        }
    }


    // checks if the given key exists in the map
    // returns true if yes; false otherwise
    public boolean contains(String key) {
        return true;
    }


    // returns value at the given key
    // returns null if key does not exist
    public T get(String key) {
        if (!this.contains(key)) {
            return null;
        }
        return null;
    }


    // returns the number of key-value mappings in the map
    public int size() {
        return this.getLength();
    }


    // returns true if there are no key-value mappings; false otherwise
    public boolean isEmpty() {
        if (this.getLength() == 0) {
            return true;
        }
        return false;
    }


    // replaces the entry for the specified key
    // only works if the key is mapped to some value
    public T replace(String key, T value) {
        return null;
    }


    // abstract method from Iterable<T>
    public Iterator<T> iterator() {
        return null;
    }
}
