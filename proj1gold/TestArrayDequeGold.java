import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /**
     * @source  ./StudentArrayDeque.java
     */
    @Test
    public void testArrayDequeGold() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String log = "";
        int firstNum = 0;
        int lastNum = 0;

        for (int j = 0; j < 100; j += 1) {
            int numberBetweenZeroAndOne = StdRandom.uniform(4);
            int i = StdRandom.uniform(100);
            if (numberBetweenZeroAndOne == 0) {
                student.addLast(i);
                solution.addLast(i);
                lastNum += 1;
                log += "addLast(" + i + ")\n";
            } else if (numberBetweenZeroAndOne == 1) {
                student.addFirst(i);
                solution.addFirst(i);
                firstNum += 1;
                log += "addFirst(" + i + ")\n";
            } else if (numberBetweenZeroAndOne == 2) {
                if (firstNum == 0) {
                    continue;
                } else {
                    firstNum -= 1;
                    log += "removeFirst()\n";
                    assertEquals(log, solution.removeFirst(), student.removeFirst());
                }
            } else {
                if (lastNum == 0) {
                    continue;
                } else {
                    lastNum -= 1;
                    log += "removeLast()\n";
                    assertEquals(log, solution.removeLast(), student.removeLast());
                }
            }
        }

    }

}
