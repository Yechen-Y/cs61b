package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Room extends Rectangle {
    Position p;
    int width;
    int height;
    TETile[][] world;

    public Room(Position p, int width, int height, TETile[][] world) {
        this.p = p;
        this.width = width;
        this.height = height;
        this.world = world;
    }

    // 画出该房间 必须先画墙壁 后地板 应该我们采用的是覆盖的方法
    public void roomGenerate() {
        drawWall();
        drawFloor();
    }

    // 画出该房间所占的墙壁空间
    private void drawWall() {
        Position[] aArray = Room.aroundCorner(p, width, height);

        for (int index = aArray[0].x; index <= aArray[1].x; index += 1) {
            for (int index2 = aArray[0].y; index2 <= aArray[2].y; index2 += 1) {
                world[index][index2] = Tileset.WALL;
            }
        }
    }

    //画出该房间的地板
    private void drawFloor() {
        Position[] pArray = Room.cornerPos(p, width, height);

        for (int index = pArray[0].x; index <= pArray[1].x; index += 1) {
            for (int index2 = pArray[0].y; index2 <= pArray[2].y; index2 += 1) {
                world[index][index2] = Tileset.FLOOR;
            }
        }

    }

    // check if the room is out of  the world
    public boolean isOutOfWorld() {
        return p.x + width > world.length - 2 || p.y + height > world[0].length - 2;
    }

    //check if the room is overlap
    public boolean isOverLap(Room r) {
        return isInside(r) || containPos(r);
    }

    // check if has a corner position is in the room
    private boolean containPos(Room r) {
        Position[] rCorner = cornerPos(r.p, r.width, r.height);
        for (int index = 0; index < 4; index += 1) {
            if ((rCorner[index].x >= p.x && rCorner[index].x <= p.x + width - 1) || (rCorner[index].y >= p.y && rCorner[index].y <= p.y + height - 1)) {
                return true;
            }
        }
        return false;
    }

    //check if the room is insideOf the previous room or equal position
    private boolean isInside(Room r) {
        Position[] rCorner = cornerPos(r.p, r.width, r.height);
        Position[] thisCorner = cornerPos(p, width, height);
        return thisCorner[0].x >= rCorner[0].x && thisCorner[0].y >= rCorner[0].y && thisCorner[2].x <= rCorner[2].x && thisCorner[2].y <= rCorner[2].y;
    }

    //生成单个随机的房间
    public static Room randomRoom(Random RANDOM, TETile[][] world) {
        int x = RandomUtils.uniform(RANDOM, 1, world.length);
        int y = RandomUtils.uniform(RANDOM, 1, world[0].length);
        int wid = RandomUtils.uniform(RANDOM, 1, 9);
        int hei = RandomUtils.uniform(RANDOM, 1, 9);
        Position p = new Position(x, y);

        return new Room(p, wid, hei, world);
    }

    //生成世界中随机的房间
    public static List<Room> RoomsGenerate(Random RANDOM, TETile[][] world) {
        int maxRoomNum = RandomUtils.uniform(RANDOM, 100, 150);
        List<Room> rooms = new LinkedList<>();
        Room room;

        for (int index = 0; index < maxRoomNum; index += 1) {
            room = randomRoom(RANDOM, world);
            if (rooms.isEmpty()) {
                if (room.isOutOfWorld()) {

                } else {
                    rooms.add(room);
                }
            } else {
                if (room.isOutOfWorld() || room.isOverLapPrevious(rooms)) {

                } else {
                    rooms.add(room);
                }
            }

        }

        for (Room r : rooms) {
            r.roomGenerate();
        }
        return rooms;
    }

    public boolean isOverLapPrevious (List<Room> rooms) {
        for (Room r : rooms) {
            if (isOverLap(r)) {
                return true;
            }
        }
        return false;
    }
}
