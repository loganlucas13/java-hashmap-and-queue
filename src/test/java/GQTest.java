import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Iterator;

public class GQTest {

    // constructor testing

    @Test
    void queueConstructorTestInteger() {
        Integer number = 4;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + intQueue.getHead().data + "\n");

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getHead(), intQueue.getTail());
        assertEquals(intQueue.getLength(), 1);
    }

    @Test
    void queueConstructorTestLong() {
        Long number = 913L; // the L specifies that 913 is a Long
        GenericQueue<Long> longQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + longQueue.getHead().data + "\n");

        assertEquals(longQueue.getHead().data, number);
        assertEquals(longQueue.getHead(), longQueue.getTail());
        assertEquals(longQueue.getLength(), 1);
    }

    @Test
    void queueConstructorTestFloat() {
        Float number = 23.2F; // the F specifies that 23.2 is a Float
        GenericQueue<Float> floatQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + floatQueue.getHead().data + "\n");

        assertEquals(floatQueue.getHead().data, number);
        assertEquals(floatQueue.getHead(), floatQueue.getTail());
        assertEquals(floatQueue.getLength(), 1);
    }

    @Test
    void queueConstructorTestString() {
        String data = "head data here!";
        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        // System.out.print("Head: " + stringQueue.getHead().data + "\n");

        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getHead(), stringQueue.getTail());
        assertEquals(stringQueue.getLength(), 1);
    }


    @Test
    void queueCodeConstructorTestInteger() {
        Integer number = 8;
        int code = 4;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number, code);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getHead().code, code);
        assertEquals(intQueue.getHead(), intQueue.getTail());
        assertEquals(intQueue.getLength(), 1);
    }

    @Test
    void queueCodeConstructorTestLong() {
        Long number = 13L;
        int code = 928;
        GenericQueue<Long> longQueue = new GenericQueue<Long>(number, code);

        assertEquals(longQueue.getHead().data, number);
        assertEquals(longQueue.getHead().code, code);
        assertEquals(longQueue.getHead(), longQueue.getTail());
        assertEquals(longQueue.getLength(), 1);
    }

    @Test
    void QueueCodeConstructorTestFloat() {
        Float number = 192.5F;
        int code = 1;
        GenericQueue<Float> floatQueue = new GenericQueue<Float>(number, code);

        assertEquals(floatQueue.getHead().data, number);
        assertEquals(floatQueue.getHead().code, code);
        assertEquals(floatQueue.getHead(), floatQueue.getTail());
        assertEquals(floatQueue.getLength(), 1);
    }

    @Test
    void QueueCodeConstructorTestString() {
        String data = "first node here!";
        int code = 101475;
        GenericQueue<String> stringQueue = new GenericQueue<String>(data, code);

        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getHead().code, code);
        assertEquals(stringQueue.getHead(), stringQueue.getTail());
        assertEquals(stringQueue.getLength(), 1);
    }


    // method testing

    @Test
    void addNoCodeTest1() {
        Integer number = 0;
        Integer numberToAdd = 6;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getLength(), 1);

        intQueue.add(numberToAdd);
        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, numberToAdd);
        assertEquals(intQueue.getLength(), 2);
    }

    @Test
    void addNoCodeTest2() {
        Integer number = 50;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getLength(), 1);

        // starting from 1 so getLength only needs to be incremented by 1
        for (int i = 1; i <= 25; i++) {
            intQueue.add(i);
            assertEquals(intQueue.getHead().data, number);
            assertEquals(intQueue.getTail().data, i);
            assertEquals(intQueue.getLength(), i+1);
        }
    }

    @Test
    void addNoCodeTest3() {
        String data = "head here!";
        String dataToAdd = "added data here!";
        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getLength(), 1);

        stringQueue.add(dataToAdd);
        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getTail().data, dataToAdd);
        assertEquals(stringQueue.getLength(), 2);
    }

    @Test
    void addNoCodeTest4() {
        // tests adding to an empty list
        Integer number = 4;
        Integer numberTwo = 5;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getLength(), 1);

        intQueue.delete(); // delete() is assumed to function correctly; unit tests for delete() are found below
        assertEquals(intQueue.getHead(), null);
        assertEquals(intQueue.getTail(), null);
        assertEquals(intQueue.getLength(), 0);

        intQueue.add(number);
        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        intQueue.add(numberTwo);
        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, numberTwo);
        assertEquals(intQueue.getLength(), 2);
    }


    @Test
    void addCodeTest1() {
        Integer number = 7;
        Integer numberToAdd = 12;
        int codeToAdd = 2;

        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getLength(), 1);

        intQueue.add(numberToAdd, codeToAdd);
        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, numberToAdd);
        assertEquals(intQueue.getTail().code, codeToAdd);
        assertEquals(intQueue.getLength(), 2);
    }

    @Test
    void addCodeTest2() {
        Integer number = 0;

        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getLength(), 1);

        for (int i = 1; i <= 50; i++) {
            intQueue.add(i, i+1); // data: i | code: i+1
            assertEquals(intQueue.getHead().data, number);
            assertEquals(intQueue.getTail().data, i);
            assertEquals(intQueue.getTail().code, i+1);
            assertEquals(intQueue.getLength(), i+1);
        }
    }

    @Test
    void addCodeTest3() {
        String data = "head here!";
        String dataToAdd = "new data here!";
        int codeToAdd = 1;

        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getLength(), 1);

        stringQueue.add(dataToAdd, codeToAdd);
        assertEquals(stringQueue.getHead().data, data);
        assertEquals(stringQueue.getTail().data, dataToAdd);
        assertEquals(stringQueue.getTail().code, codeToAdd);
        assertEquals(stringQueue.getLength(), 2);
    }


    @Test
    void deleteTest1() {
        // tests delete() with 1 node in the list
        Integer number = 2;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        Integer returnVal = intQueue.delete();

        assertEquals(returnVal, number);
        assertEquals(intQueue.getHead(), null);
        assertEquals(intQueue.getTail(), null);
        assertEquals(intQueue.getLength(), 0);
    }

    @Test
    void deleteTest2() {
        // tests delete() with 2 nodes in the list
        // also tries to delete when length == 0
        Integer number = 7;
        Integer numberTwo = 9;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        intQueue.add(numberTwo);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, numberTwo);
        assertEquals(intQueue.getLength(), 2);

        // deletes first node
        Integer returnVal = intQueue.delete();
        assertEquals(returnVal, number);
        assertEquals(intQueue.getHead().data, numberTwo);
        assertEquals(intQueue.getTail().data, numberTwo);
        assertEquals(intQueue.getLength(), 1);

        // deletes second node
        returnVal = intQueue.delete();
        assertEquals(returnVal, numberTwo);
        assertEquals(intQueue.getHead(), null);
        assertEquals(intQueue.getTail(), null);
        assertEquals(intQueue.getLength(), 0);

        // attempts to delete a node that doesn't exist
        returnVal = intQueue.delete();
        assertEquals(returnVal, null);
    }

    @Test
    void deleteTest3() {
        // tests delete using a loop
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);


        for (int i = 1; i <= 50; i++) {
            intQueue.add(i);
            assertEquals(intQueue.getLength(), i+1);
        }

        int length = intQueue.getLength();
        for (int i = 0; i <= 50; i++) {
            Integer expectedVal = intQueue.getHead().data;
            Integer returnVal = intQueue.delete();
            assertEquals(returnVal, expectedVal);
            assertEquals(intQueue.getLength(), length-1);
            length--;
        }
    }

    @Test
    void deleteTest4() {
        // tests delete() with a string
        String data = "head here!";
        GenericQueue<String> stringQueue = new GenericQueue<String>(data);

        assertEquals(stringQueue.getHead().data, "head here!");
        assertEquals(stringQueue.getTail().data, "head here!");
        assertEquals(stringQueue.getLength(), 1);

        String returnVal = stringQueue.delete();

        assertEquals(returnVal, data);
        assertEquals(stringQueue.getHead(), null);
        assertEquals(stringQueue.getTail(), null);
        assertEquals(stringQueue.getLength(), 0);
    }

    @Test
    void deleteTest5() {
        // tests delete() with an empty list and a double
        Double number = 1.5;
        GenericQueue<Double> doubleQueue = new GenericQueue<Double>(number);

        assertEquals(doubleQueue.getHead().data, number);
        assertEquals(doubleQueue.getTail().data, number);
        assertEquals(doubleQueue.getLength(), 1);

        Double returnVal = doubleQueue.delete();
        assertEquals(returnVal, number);
        assertEquals(doubleQueue.getHead(), null);
        assertEquals(doubleQueue.getTail(), null);
        assertEquals(doubleQueue.getLength(), 0);

        returnVal = doubleQueue.delete();
        assertEquals(returnVal, null);
        assertEquals(doubleQueue.getHead(), null);
        assertEquals(doubleQueue.getTail(), null);
        assertEquals(doubleQueue.getLength(), 0);
    }


    @Test
    void enqueueTest() {
        // this function only calls add(), so not much extra testing is needed
        Integer number = 8;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        for (int i = 1; i <= 25; i++) {
            intQueue.enqueue(i);
            assertEquals(intQueue.getHead().data, number);
            assertEquals(intQueue.getTail().data, i);
            assertEquals(intQueue.getLength(), i+1);
        }
    }


    @Test
    void dequeueTest() {
        // this function only calls delete(), so not much extra testing is needed
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        for (int i = 1; i <= 50; i++) {
            intQueue.enqueue(i);
            assertEquals(intQueue.getHead().data, number);
            assertEquals(intQueue.getTail().data, i);
            assertEquals(intQueue.getLength(), i+1);
        }

        int length = intQueue.getLength();
        for (int i = 0; i <= 50; i++) {
            Integer expectedVal = intQueue.getHead().data;
            Integer returnVal = intQueue.dequeue();
            assertEquals(expectedVal, returnVal);
            assertEquals(intQueue.getLength(), length-1);
            length--;
        }
    }


    @Test
    void searchForCodeTest1() {
        Integer number = 5;
        int code = 10;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number, code);

        Integer result = intQueue.searchForCode(code);

        assertEquals(result, number);

    }

    @Test
    void searchForCodeTest2() {
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        for (int i = 1; i < 50; i++) {
            intQueue.add(i, i+1);
        }

        Integer result1 = intQueue.searchForCode(2);
        Integer result2 = intQueue.searchForCode(50);
        Integer result3 = intQueue.searchForCode(300);

        assertEquals(result1, 1);
        assertEquals(result2, 49);
        assertEquals(result3, null);
    }

    @Test
    void searchForCodeTest3() {
        // checks in a loop over an empty list
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        intQueue.delete();

        for (int i = 0; i < 50; i++) {
            assertEquals(intQueue.searchForCode(i), null);
        }
    }


    @Test
    void replaceAtCodeTest1() {
        Integer number = 1;
        Integer newNumber = 5;
        int code = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number, code);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        Integer returnVal = intQueue.replaceAtCode(newNumber, code);

        assertEquals(intQueue.getHead().data, newNumber);
        assertEquals(intQueue.getTail().data, newNumber);
        assertEquals(intQueue.getLength(), 1);
        assertEquals(returnVal, number);
    }

    @Test
    void replaceAtCodeTest2() {
        Integer number = 0;
        Integer newNumber = 927;
        int code = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number, code);

        for (int i = 1; i < 50; i++) {
            intQueue.add(i, i+1);
        }

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, 49); // final value from loop
        assertEquals(intQueue.getLength(), 50);

        Integer returnVal = intQueue.replaceAtCode(newNumber, 37);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, 49);
        assertEquals(intQueue.getLength(), 50);
        assertEquals(returnVal, 36);
        assertEquals(intQueue.searchForCode(37), newNumber);
    }

    @Test
    void replaceAtCodeTest3() {
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);

        Integer returnVal = intQueue.replaceAtCode(200, 200);

        assertEquals(intQueue.getHead().data, number);
        assertEquals(intQueue.getTail().data, number);
        assertEquals(intQueue.getLength(), 1);
        assertEquals(returnVal, null);
    }


    @Test
    void printTest1() {
        // prints with multiple nodes
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        for (int i = 1; i < 10; i++) {
            intQueue.add(i);
        }

        // should print out from 0-9 with a newline between each number
        intQueue.print();
    }

    @Test
    void printTest2() {
        // tests with an empty list
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        Integer returnVal = intQueue.delete();

        assertEquals(returnVal, number);

        // should print "Empty List"
        intQueue.print();
    }


    @Test
    void dumpListTest() {
        // tests with multiple nodes
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        for (int i = 1; i < 10; i++) {
            intQueue.add(i);
        }

        ArrayList<Integer> expectedResult = new ArrayList<Integer>(10);
        for (int i = 0; i < 10; i++) {
            expectedResult.add(i);
        }

        assertTrue(intQueue.dumpList().equals(expectedResult));
    }


    // iterator tests

    @Test
    void GLLIteratorTest1() {
        // tests 'hasNext' with no next value
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);
        intQueue.delete();

        Iterator<Integer> iterator = intQueue.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void GLLIteratorTest2() {
        // tests 'hasNext' with a next value
        Integer number = 0;
        Integer numberTwo = 5;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);
        intQueue.add(numberTwo);

        Iterator<Integer> iterator = intQueue.iterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void GLLIteratorTest3() {
        // tests 'next' with a list of size 1
        Integer number = 0;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);
        Iterator<Integer> iterator = intQueue.iterator();

        Integer returnVal = iterator.next();

        assertEquals(returnVal, number);

        returnVal = iterator.next();

        assertEquals(returnVal, null);
    }

    @Test
    void GLLIteratorTest4() {
        // tests with a for each loop
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        for (int i = 1; i < 50; i++) {
            intQueue.add(i);
        }

        int expectedVal = 0;
        for (Integer value : intQueue) {
            assertEquals(expectedVal, value);
            expectedVal++;
        }
    }


    @Test
    void ReverseGLLIteratorTest1() {
        // tests 'hasNext' with no next value
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);
        intQueue.delete();

        Iterator<Integer> iterator = intQueue.descendingIterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void ReverseGLLIteratorTest2() {
        // tests 'hasNext' with a next value
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        Iterator<Integer> iterator = intQueue.descendingIterator();

        assertTrue(iterator.hasNext());
    }

    @Test
    void ReverseGLLIteratorTest3() {
        // tests 'next' with a list of size 1
        Integer number = 9;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        Iterator<Integer> iterator = intQueue.descendingIterator();

        Integer returnVal = iterator.next();

        assertEquals(number, returnVal);
    }

    @Test
    void ReverseGLLIteratorTest4() {
        // tests with a for each loop
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        for (int i = 1; i < 50; i++) {
            intQueue.add(i);
        }

        Iterator<Integer> iterator = intQueue.descendingIterator();

        int expectedVal = 49;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), expectedVal);
            expectedVal--;
        }
    }
}