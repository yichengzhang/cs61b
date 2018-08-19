import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void TestResize(){
        ArrayDeque<Integer> intArray = new ArrayDeque<>();
        for (int i = 0; i < 10000; i++){
            intArray.addFirst(1);
        }
        for (int i = 0; i < 9999; i++) {
            intArray.removeLast();
        }
        assertEquals(1, intArray.size());
        assertEquals(8, intArray.capacity());
    }
    @Test
    public void testAddRemove() throws Exception {
        int[] expected = {1, 2, 3};

        ArrayDeque<Integer> intArray = new ArrayDeque<>();
        intArray.addLast(4);
        intArray.addLast(5);
        intArray.addFirst(3);
        intArray.addFirst(2);
        intArray.addLast(6);
        intArray.addFirst(1);
        intArray.addFirst(0);
        intArray.removeLast();
        intArray.removeFirst();
        intArray.removeLast();
        intArray.removeLast();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],intArray.get(i).intValue());
        }

    }

}
