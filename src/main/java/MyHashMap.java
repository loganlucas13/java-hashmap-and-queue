import java.util.Iterator;
import java.util.ArrayList;

public class MyHashMap<T> implements Iterable<T> {

    // data members
    private ArrayList<GenericQueue<T>> map;
    private int length;


    // constructor
    MyHashMap(String key, T value) {
        this.map = new ArrayList<GenericQueue<T>>(10);
        for (int i = 0; i < 10; i++) {
            this.map.add(null); // placeholder values - indexing without these creates an error
        }

        this.setLength(0); // is incremented in the call to put()
        this.put(key, value);
    }


    // getters and setters
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

    // creates a hash code given a key
    // the hash code is stored in each nodes 'code' data field
    int createHashCode(String key) {
        return key.hashCode();
    }


    // creates a hash value given the key
    // the hash value is the index inside of 'map'
    int createHashValue(String key) {
        return createHashCode(key)&9; // for ArrayList of size 10
    }


    // 1. creates a hash code + value from the key
    // 2. checks if there is a GenericQueue at the index in the map
    //   -> if not: creates a new GenericQueue and adds it to the map at the index of the hash value
    //   -> otherwise: appends a new node to the existing GenericQueue at the index
    public void put(String key, T value) {
        int hashCode = createHashCode(key);
        int index = createHashValue(key);

        // duplicate key; updates value of node to new value
        // length is not incremented since no new node is added
        if (this.contains(key)) {
            this.replace(key, value);
            return;
        }

        if (this.map.get(index) == null) {
            this.map.set(index, new GenericQueue<>(value, hashCode));
        }
        else {
            this.map.get(index).add(value, hashCode);
        }
        this.setLength(this.getLength() + 1);
    }


    // checks if the given key exists in the map
    // returns true if yes; false otherwise
    public boolean contains(String key) {
        int hashCode = createHashCode(key);
        int index = createHashValue(key);

        GenericQueue<T> queueToSearch = this.map.get(index);
        if (queueToSearch == null) {
            return false;
        }
        else if (queueToSearch.searchForCode(hashCode) == null) {
            return false;
        }
        return true;
    }


    // returns value at the given key
    // returns null if key does not exist
    public T get(String key) {
        if (!this.contains(key)) {
            return null;
        }

        int hashCode = createHashCode(key);
        int index = createHashValue(key);

        return this.map.get(index).searchForCode(hashCode);
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
        if (!this.contains(key)) {
            return null;
        }

        int hashCode = createHashCode(key);
        int index = createHashValue(key);

        GenericQueue<T> queueToSearch = this.map.get(index);
        return queueToSearch.replaceAtCode(value, hashCode);
    }


    // abstract method from Iterable<T>
    public Iterator<T> iterator() {
        return new HMIterator<>(this.map);
    }
}
