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
}
