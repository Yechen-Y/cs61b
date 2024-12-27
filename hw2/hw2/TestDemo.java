package hw2;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestDemo {

    @Test
    public void testInit() {
        Percolation p = new Percolation(4);
        p.open(0,0);
        p.open(1, 3);
        assertTrue(p.isOpen(1, 3));
        p.open(0, 3);
        assertTrue(p.isFull(1, 3));
        p.open(1,2);
        p.open(2, 2);
        p.open(3, 2);
        assertTrue(p.percolates());
        assertEquals(6, p.numberOfOpenSites());
        System.out.println(StdRandom.uniform(100));
        System.out.println(StdRandom.uniform(100));
        PercolationFactory q = new PercolationFactory();
        PercolationStats test = new PercolationStats(5, 40, q);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceHigh());
        System.out.println(test.confidenceLow());
    }
}
