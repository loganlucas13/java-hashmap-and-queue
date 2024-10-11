import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
}