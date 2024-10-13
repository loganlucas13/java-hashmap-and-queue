import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;
public class HMTest {

    // constructor testing

    @Test
    void hashMapConstructorTestInteger() {
        String key = "integer test";
        Integer value = 27;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void hashMapConstructorTestLong() {
        String key = "long test";
        Long value = 925000L;

        MyHashMap<Long> hashMap = new MyHashMap<Long>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void hashMapConstructorTestFloat() {
        String key = "long test";
        Float value = 12.8F;

        MyHashMap<Float> hashMap = new MyHashMap<Float>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void hashMapConstructorTestString() {
        String key = "string test";
        String value = "first value here!";

        MyHashMap<String> hashMap = new MyHashMap<String>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);
    }


    // method testing

    //

    @Test
    void putTest1() {
        // calling put() on keys with different hash values
        // creates a new queue
        String key1 = "first key";
        String key2 = "second key";
        Integer value1 = 1;
        Integer value2 = 2;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value1);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);

        assertNotEquals(index1, index2);

        assertEquals(hashMap.get(key1), value1);
        assertEquals(hashMap.size(), 1);

        hashMap.put(key2, value2);

        assertEquals(hashMap.get(key1), value1);
        assertEquals(hashMap.get(key2), value2);
        assertEquals(hashMap.size(), 2);
    }

    @Test
    void putTest2() {
        // calls put() on the same key
        // intended behavior is to replace the value at the key
        String key = "key";
        Integer value1 = 1;
        Integer value2 = 2;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value1);

        assertEquals(hashMap.get(key), value1);
        assertEquals(hashMap.size(), 1);

        hashMap.put(key, value2);

        assertEquals(hashMap.get(key), value2);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void putTest3() {
        // calls put() on keys with the same hash value
        // adds to the end of the queue at the index
        String key1 = "Cozmo";
        String key2 = "omzoC";
        Integer value1 = 1;
        Integer value2 = 2;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value1);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);

        assertEquals(index1, index2);

        assertEquals(hashMap.get(key1), value1);
        assertEquals(hashMap.size(), 1);

        hashMap.put(key2, value2);

        assertEquals(hashMap.get(key1), value1);
        assertEquals(hashMap.get(key2), value2);
        assertEquals(hashMap.size(), 2);
    }

    @Test
    void containsTest1() {
        // tests with an empty queue at the hash value/index
        String key1 = "first key";
        String key2 = "second key";
        Integer value = 12082023;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);

        assertNotEquals(index1, index2);

        assertEquals(hashMap.get(key1), value);
        assertEquals(hashMap.size(), 1);

        boolean returnVal = hashMap.contains(key2);
        assertEquals(returnVal, false);
    }

    @Test
    void containsTest2() {
        // tests with a non-empty queue at the index, but not including the key
        String key1 = "Cozmo";
        String key2 = "omzoC";
        Integer value = 127;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);

        assertEquals(index1, index2);

        assertEquals(hashMap.get(key1), value);
        assertEquals(hashMap.size(), 1);

        boolean returnVal = hashMap.contains(key2);
        assertEquals(returnVal, false);
    }

    @Test
    void containsTest3() {
        // tests with a value at the key
        String key = "Cozmo";
        Integer value = 127;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);

        boolean returnVal = hashMap.contains(key);
        assertEquals(returnVal, true);
    }


    @Test
    void getTest1() {
        // tests with the value existing
        String key = "abcdefghijklmnopqrstuvwxyz";
        Integer value = 84;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void getTest2() {
        // tests with value not existing
        // intended behavior is to return 'null'
        String key1 = "mouse";
        String key2 = "value doesn't exist";
        Integer value = 84;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value);

        assertEquals(hashMap.get(key2), null);
        assertEquals(hashMap.size(), 1);
    }


    @Test
    void sizeTest1() {
        // tests with 1 node
        String key = "alfredo";
        Integer value = 99;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.size(), 1);
    }

    @Test
    void sizeTest2() {
        // tests with 3 nodes, some with different hashValues and some with the same
        // includes a node that had a duplicate key, so 4 put() calls were made
        // (including the one in the constructor) but only 3 nodes are created
        String key1 = "first key";
        String key2 = "Cozmo";
        String key3 = "omzoC";
        Integer value1 = 1;
        Integer value2 = 2;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value1);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);
        int index3 = hashMap.createHashValue(key3);

        assertNotEquals(index1, index2);
        assertEquals(index2, index3);

        hashMap.put(key1, value2);
        hashMap.put(key2, value1);
        hashMap.put(key3, value2);

        assertEquals(hashMap.size(), 3);
    }


    @Test
    void isEmptyTest() {
        // since there is no way to remove nodes and no way to create an empty hashmap
        // with the current setup, isEmpty() effectively only returns false
        // this test shows that it will return false with a non-empty list
        String key = "moose";
        Integer value = 4;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        boolean returnVal = hashMap.isEmpty();

        assertEquals(returnVal, false);
    }


    @Test
    void replaceTest1() {
        // replaces with a value at the key
        String key = "replace test";
        Integer value = 8;
        Integer newValue = 58;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.get(key), value);
        assertEquals(hashMap.size(), 1);

        Integer returnVal = hashMap.replace(key, newValue);

        assertEquals(returnVal, value);
        assertEquals(hashMap.get(key), newValue);
        assertEquals(hashMap.size(), 1);
    }

    @Test
    void replaceTest2() {
        // attempts to replace with no value at the key
        // intended behavior is to do nothing and return null
        String key1 = "replace test 2";
        String key2 = "does not exist";
        Integer value = 10;
        Integer newValue = 57;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value);

        assertEquals(hashMap.get(key1), value);
        assertEquals(hashMap.size(), 1);

        Integer returnVal = hashMap.replace(key2, newValue);

        assertEquals(returnVal, null);
        assertEquals(hashMap.get(key1), value);
        assertEquals(hashMap.get(key2), null);
        assertEquals(hashMap.size(), 1);
    }


    @Test
    void getAndSetLengthTest() {
        String key = "getting and setting test";
        Integer value = 5;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertEquals(hashMap.getLength(), 1);

        hashMap.setLength(200);

        assertEquals(hashMap.getLength(), 200);
    }


    @Test
    void createHashCodeAndValueTest() {
        String key = "carrot";
        Integer value = 92;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        int expectedCode = key.hashCode();
        int expectedValue = key.hashCode()&9;

        int hashCode = hashMap.createHashCode(key);
        int hashValue = hashMap.createHashValue(key);

        assertEquals(expectedCode, hashCode);
        assertEquals(expectedValue, hashValue);
    }


    // iterator tests

    @Test
    void HMIteratorTest1() {
        // tests 'hasNext' with 1 value in the list
        String key = "key";
        Integer value = 9;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key, value);

        assertTrue(hashMap.iterator().hasNext());
    }

    @Test
    void HMIteratorTest2() {
        // tests 'hasNext' with 3 values in the list
        String key1 = "key";
        String key2 = "another key";
        String key3 = "another one";
        Integer value = 4;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value);
        hashMap.put(key2, value);
        hashMap.put(key3, value);

        assertTrue(hashMap.iterator().hasNext());
    }

    @Test
    void HMIteratorTest3() {
        // tests 'next' with 4 values in the list
        // mix of different and same hash values (indices)
        String key1 = "first key";
        String key2 = "Cozmo";
        String key3 = "mouse";
        String key4 = "omzoC";
        Integer value1 = 5;
        Integer value2 = 10;
        Integer value3 = 15;
        Integer value4 = 20;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value1);
        hashMap.put(key2, value2);
        hashMap.put(key3, value3);
        hashMap.put(key4, value4);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);
        int index3 = hashMap.createHashValue(key3);
        int index4 = hashMap.createHashValue(key4);

        assertNotEquals(index1, index2);
        assertNotEquals(index1, index3);
        assertNotEquals(index2, index3);

        assertEquals(index2, index4); // 'Cozmo' and 'omzoc'

        assertEquals(hashMap.size(), 4);

        Iterator<Integer> iterator = hashMap.iterator();

        assertEquals(iterator.next(), 10); // key 2 (index 0)
        assertEquals(iterator.next(), 20); // key 4 (index 0)
        assertEquals(iterator.next(), 15); // key 3 (index 1)
        assertEquals(iterator.next(), 5); // key 1 (index 9)
        assertEquals(iterator.next(), null); // none left
    }

    @Test
    void HMIteratorTest4() {
        // all keys have the same hash value
        // uses a foreach loop
        String key1 = "hamster";
        String key2 = "rooster";
        String key3 = "Cozmo";
        String key4 = "omzoC";
        Integer value1 = 10;
        Integer value2 = 15;
        Integer value3 = 20;
        Integer value4 = 25;

        MyHashMap<Integer> hashMap = new MyHashMap<Integer>(key1, value1);

        int index1 = hashMap.createHashValue(key1);
        int index2 = hashMap.createHashValue(key2);
        int index3 = hashMap.createHashValue(key3);
        int index4 = hashMap.createHashValue(key4);

        assertEquals(index1, index2);
        assertEquals(index2, index3);
        assertEquals(index3, index4);

        hashMap.put(key2, value2);
        hashMap.put(key3, value3);
        hashMap.put(key4, value4);

        assertEquals(hashMap.size(), 4);

        Integer expectedValue = 10;
        for (Integer value : hashMap) {
            assertEquals(value, expectedValue);
            expectedValue += 5;
        }
    }
}
