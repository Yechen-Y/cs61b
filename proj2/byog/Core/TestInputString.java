package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class TestInputString {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 80;
    Random ran = new Random(1134);
    @Test
    public void test1() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        List<Room> r =Room.RoomsGenerate(ran, world);

        ter.renderFrame(world);
        while (true) {

        }
    }
}
