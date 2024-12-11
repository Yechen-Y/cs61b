import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    public static OffByN offBy5 = new OffByN(5);

    @Test
    public void testOffByN() {
        assertTrue(offBy5.equalChars('a','f'));
    }
}
