import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
public class GQTest {

    /*
    types to test:
        1. Byte
        2. Short
        3. Integer
        4. Long
        5. Float
        6. Double
        7. Character
        8. Boolean
    */

    @Test
    void queueConstructorTestInteger() {
        Integer number = 4;
        GenericQueue<Integer> intQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + intQueue.getHead().data + "\n");

        assert(intQueue.getHead().data == number);
        assert(intQueue.getHead() == intQueue.tail);
        assert(intQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestLong() {
        Long number = 913L; // the L specifies that 913 is a Long
        GenericQueue<Long> longQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + longQueue.getHead().data + "\n");

        assert(longQueue.getHead().data == number);
        assert(longQueue.getHead() == longQueue.tail);
        assert(longQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestFloat() {
        Float number = 23.2F; // the F specifies that 23.2 is a Float
        GenericQueue<Float> floatQueue = new GenericQueue<>(number);

        // System.out.print("Head: " + floatQueue.getHead().data + "\n");

        assert(floatQueue.getHead().data == number);
        assert(floatQueue.getHead() == floatQueue.tail);
        assert(floatQueue.getLength() == 1);
    }

    @Test
    void queueConstructorTestString() {
        String data = "head data here!";
        GenericQueue<String> stringQueue = new GenericQueue<>(data);

        // System.out.print("Head: " + stringQueue.getHead().data + "\n");

        assert(stringQueue.getHead().data == data);
        assert(stringQueue.getHead() == stringQueue.tail);
        assert(stringQueue.getLength() == 1);
    }
}
