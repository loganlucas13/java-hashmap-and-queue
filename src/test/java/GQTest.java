import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
public class GQTest {

    // constructor testing

    @Test
    void queueConstructorTestInteger() {
        Integer number = 4;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + intQueue.getHead().data + "\n");

        assert(intQueue.getHead().data == number);
        assert(intQueue.getHead() == intQueue.getTail());
        assert(intQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestLong() {
        Long number = 913L; // the L specifies that 913 is a Long
        GenericQueue<Long> longQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + longQueue.getHead().data + "\n");

        assert(longQueue.getHead().data == number);
        assert(longQueue.getHead() == longQueue.getTail());
        assert(longQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestFloat() {
        Float number = 23.2F; // the F specifies that 23.2 is a Float
        GenericQueue<Float> floatQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + floatQueue.getHead().data + "\n");

        assert(floatQueue.getHead().data == number);
        assert(floatQueue.getHead() == floatQueue.getTail());
        assert(floatQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestString() {
        String data = "head data here!";
        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        // System.out.print("Head: " + stringQueue.getHead().data + "\n");

        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getHead() == stringQueue.getTail());
        assert(stringQueue.getLength() == 1);
    }


    @Test
    void queueCodeConstructorTestInteger() {
        Integer number = 8;
        int code = 4;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number, code);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getHead().code == code);
        assert(intQueue.getHead() == intQueue.getTail());
        assert(intQueue.getLength() == 1);
    }

    @Test
    void queueCodeConstructorTestLong() {
        Long number = 13L;
        int code = 928;
        GenericQueue<Long> longQueue = new GenericQueue<Long>(number, code);

        assert(longQueue.getHead().data == number);
        assert(longQueue.getHead().code == code);
        assert(longQueue.getHead() == longQueue.getTail());
        assert(longQueue.getLength() == 1);
    }

    @Test
    void QueueCodeConstructorTestFloat() {
        Float number = 192.5F;
        int code = 1;
        GenericQueue<Float> floatQueue = new GenericQueue<Float>(number, code);

        assert(floatQueue.getHead().data == number);
        assert(floatQueue.getHead().code == code);
        assert(floatQueue.getHead() == floatQueue.getTail());
        assert(floatQueue.getLength() == 1);
    }

    @Test
    void QueueCodeConstructorTestString() {
        String data = "first node here!";
        int code = 101475;
        GenericQueue<String> stringQueue = new GenericQueue<String>(data, code);

        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getHead().code == code);
        assert(stringQueue.getHead() == stringQueue.getTail());
        assert(stringQueue.getLength() == 1);
    }


    // method testing

    @Test
    void addNoCodeTest1() {
        Integer number = 0;
        Integer numberToAdd = 6;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getLength() == 1);

        intQueue.add(numberToAdd);
        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == numberToAdd);
        assert(intQueue.getLength() == 2);
    }

    @Test
    void addNoCodeTest2() {
        Integer number = 50;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getLength() == 1);

        // starting from 1 so getLength only needs to be incremented by 1
        for (int i = 1; i <= 25; i++) {
            intQueue.add(i);
            assert(intQueue.getHead().data == number);
            assert(intQueue.getTail().data == i);
            assert(intQueue.getLength() == i+1);
        }
    }

    @Test
    void addNoCodeTest3() {
        String data = "head here!";
        String dataToAdd = "added data here!";
        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getLength() == 1);

        stringQueue.add(dataToAdd);
        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getTail().data == dataToAdd);
        assert(stringQueue.getLength() == 2);
    }

    @Test
    void addNoCodeTest4() {
        // tests adding to an empty list
        Integer number = 4;
        Integer numberTwo = 5;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getLength() == 1);

        intQueue.delete(); // delete() is assumed to function correctly; unit tests for delete() are found below
        assert(intQueue.getHead() == null);
        assert(intQueue.getTail() == null);
        assert(intQueue.getLength() == 0);

        intQueue.add(number);
        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);

        intQueue.add(numberTwo);
        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == numberTwo);
        assert(intQueue.getLength() == 2);
    }


    @Test
    void addCodeTest1() {
        Integer number = 7;
        Integer numberToAdd = 12;
        int codeToAdd = 2;

        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getLength() == 1);

        intQueue.add(numberToAdd, codeToAdd);
        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == numberToAdd);
        assert(intQueue.getTail().code == codeToAdd);
        assert(intQueue.getLength() == 2);
    }

    @Test
    void addCodeTest2() {
        Integer number = 0;

        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getLength() == 1);

        for (int i = 1; i <= 50; i++) {
            intQueue.add(i, i+1); // data: i | code: i+1
            assert(intQueue.getHead().data == number);
            assert(intQueue.getTail().data == i);
            assert(intQueue.getTail().code == i+1);
            assert(intQueue.getLength() == i+1);
        }
    }

    @Test
    void addCodeTest3() {
        String data = "head here!";
        String dataToAdd = "new data here!";
        int codeToAdd = 1;

        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getLength() == 1);

        stringQueue.add(dataToAdd, codeToAdd);
        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getTail().data == dataToAdd);
        assert(stringQueue.getTail().code == codeToAdd);
        assert(stringQueue.getLength() == 2);
    }


    @Test
    void deleteTest1() {
        // tests delete() with 1 node in the list
        Integer number = 2;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);

        Integer returnVal = intQueue.delete();

        assert(returnVal == number);
        assert(intQueue.getHead() == null);
        assert(intQueue.getTail() == null);
        assert(intQueue.getLength() == 0);
    }

    @Test
    void deleteTest2() {
        // tests delete() with 2 nodes in the list
        // also tries to delete when length == 0
        Integer number = 7;
        Integer numberTwo = 9;

        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);

        intQueue.add(numberTwo);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == numberTwo);
        assert(intQueue.getLength() == 2);

        // deletes first node
        Integer returnVal = intQueue.delete();
        assert(returnVal == number);
        assert(intQueue.getHead().data == numberTwo);
        assert(intQueue.getTail().data == numberTwo);
        assert(intQueue.getLength() == 1);

        // deletes second node
        returnVal = intQueue.delete();
        assert(returnVal == numberTwo);
        assert(intQueue.getHead() == null);
        assert(intQueue.getTail() == null);
        assert(intQueue.getLength() == 0);

        // attempts to delete a node that doesn't exist
        returnVal = intQueue.delete();
        assert(returnVal == null);
    }

    @Test
    void deleteTest3() {
        // tests delete using a loop
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);


        for (int i = 1; i <= 50; i++) {
            intQueue.add(i);
            assert(intQueue.getLength() == i+1);
        }

        int length = intQueue.getLength();
        for (int i = 0; i <= 50; i++) {
            Integer expectedVal = intQueue.getHead().data;
            Integer returnVal = intQueue.delete();
            assert(returnVal == expectedVal);
            assert(intQueue.getLength() == length-1);
            length--;
        }
    }

    @Test
    void deleteTest4() {
        // tests delete() with a string
        String data = "head here!";
        GenericQueue<String> stringQueue = new GenericQueue<String>(data);

        assert(stringQueue.getHead().data == "head here!");
        assert(stringQueue.getTail().data == "head here!");
        assert(stringQueue.getLength() == 1);

        String returnVal = stringQueue.delete();

        assert(returnVal == data);
        assert(stringQueue.getHead() == null);
        assert(stringQueue.getTail() == null);
        assert(stringQueue.getLength() == 0);
    }

    @Test
    void deleteTest5() {
        // tests delete() with an empty list and a double
        Double number = 1.5;
        GenericQueue<Double> doubleQueue = new GenericQueue<Double>(number);

        assert(doubleQueue.getHead().data == number);
        assert(doubleQueue.getTail().data == number);
        assert(doubleQueue.getLength() == 1);

        Double returnVal = doubleQueue.delete();
        assert(returnVal == number);
        assert(doubleQueue.getHead() == null);
        assert(doubleQueue.getTail() == null);
        assert(doubleQueue.getLength() == 0);

        returnVal = doubleQueue.delete();
        assert(returnVal == null);
        assert(doubleQueue.getHead() == null);
        assert(doubleQueue.getTail() == null);
        assert(doubleQueue.getLength() == 0);
    }


    @Test
    void enqueueTest() {
        // this function only calls add(), so not much extra testing is needed
        Integer number = 8;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);

        for (int i = 1; i <= 25; i++) {
            intQueue.enqueue(i);
            assert(intQueue.getHead().data == number);
            assert(intQueue.getTail().data == i);
            assert(intQueue.getLength() == i+1);
        }
    }


    @Test
    void dequeueTest() {
        // this function only calls delete(), so not much extra testing is needed
        Integer number = 0;
        GenericQueue<Integer> intQueue = new GenericQueue<Integer>(number);

        assert(intQueue.getHead().data == number);
        assert(intQueue.getTail().data == number);
        assert(intQueue.getLength() == 1);

        for (int i = 1; i <= 50; i++) {
            intQueue.enqueue(i);
            assert(intQueue.getHead().data == number);
            assert(intQueue.getTail().data == i);
            assert(intQueue.getLength() == i+1);
        }

        int length = intQueue.getLength();
        for (int i = 0; i <= 50; i++) {
            Integer expectedVal = intQueue.getHead().data;
            Integer returnVal = intQueue.dequeue();
            assert(expectedVal == returnVal);
            assert(intQueue.getLength() == length-1);
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
}