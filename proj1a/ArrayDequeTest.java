import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void testArrayDeque(){
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addLast(7);
        a.addLast(8);
        a.addFirst(9);
    }
}
