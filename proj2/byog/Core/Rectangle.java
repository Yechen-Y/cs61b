package byog.Core;

public class Rectangle {
    /** return four corner position
     *  3 * * * 2
     *  *       *
     *  *       *
     *  0 * * * 1
     */
    public static Position[] cornerPos (Position p, int width, int height) {
        Position[] pArray = new Position[4];
        pArray[0] = new Position(p.x, p.y);
        pArray[1] = new Position(p.x + width - 1, p.y);
        pArray[2] = new Position(p.x + width - 1, p.y + height - 1);
        pArray[3] = new Position(p.x, p.y + height - 1);
        return pArray;
    }

    /** return four aroundCorner position
     *  3 * * * 2
     *  *       *
     *  *       *
     *  0 * * * 1
     */
    public static Position[] aroundCorner (Position p, int width, int height) {
        Position[] pArray = new Position[4];
        pArray[0] = new Position(p.x - 1, p.y - 1);
        pArray[1] = new Position(p.x + width, p.y - 1);
        pArray[2] = new Position(p.x + width, p.y + height);
        pArray[3] = new Position(p.x - 1, p.y + height);
        return pArray;
    }
}
