package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

//should return a TETile[][]
public class WorldGenerate {
    private int width;
    private int height;
    private int seed;
    public TETile nothing = Tileset.NOTHING;
    public TETile wall = Tileset.WALL;
    public TETile floor = Tileset.FLOOR;
    public TETile locked = Tileset.LOCKED_DOOR;

    public WorldGenerate (int width, int height, int seed) {
        this.width = width;
        this.height = height;
        this.seed = seed;
    }
}
