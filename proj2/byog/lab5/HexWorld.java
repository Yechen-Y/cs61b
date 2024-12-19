package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     *
     * @param rowNum the bottom of the row is 0
     * @param size hexagon of side length
     * @return the rowWidth
     */
    public static int rowWidth (int rowNum, int size) {
        int rowWidth;
        if (rowNum < size) {
            rowWidth = size + 2 * rowNum;
        } else {
            rowWidth = size + 2 * (size - 1) - 2 * (rowNum - size);
        }
        return rowWidth;
    }

    @Test
    public void testRowWidth() {
        assertEquals(2, rowWidth(0, 2));
        assertEquals(4, rowWidth(1, 2));
    }

    public static int rowOffSet(int rowNum, int size) {
        int rowOffSet;
        if (rowNum < size) {
            rowOffSet = rowNum;
        } else {
            rowOffSet = 2 * size - rowNum - 1;
        }
        return rowOffSet;
    }

    @Test
    public void testRowOffSet() {
        assertEquals(0, rowOffSet(0, 3));
        assertEquals(2, rowOffSet(5, 4));
    }

    public static void addRow(TETile[][] world, int rowNum, int size, TETile t, Position p) {
        int width = rowWidth(rowNum, size);
        int offset = rowOffSet(rowNum, size);
        for (int index = 0; index < width; index += 1) {
            world[p.x - offset + index][p.y + rowNum] = t;
        }
    }


    public static void addHexagon(TETile[][] world, int size, Position p, TETile t) {
        for (int rowNum = 0; rowNum < 2 * size; rowNum += 1) {
            addRow(world, rowNum, size, t, p);
        }
    }

    public static void main (String[] args) {
        int WIDTH = 60;
        final int HEIGHT = 30;

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(5, 5);

        addHexagon(world, 4, p, Tileset.FLOWER);
        ter.renderFrame(world);
    }

}

