package com.darkpaster.pixellife.level;

public enum TileType {
    EMPTY(0), GRASS1(1), WALL(2), GRASS3(3), DIRT1(4), DIRT2(5), FLOOR(6),
    TREE(7), GRASS2(8), DOOR(9);
    private int n;
    TileType(int n) {
        this.n = n;
    }

    public int getN(){
        return n;
    }

    public static TileType getTile(int n){
        switch(n){
            case 1:
                return GRASS1;
            case 2:
                return WALL;
            case 3:
                return GRASS3;
            case 4:
                return DIRT1;
            case 5:
                return DIRT2;
            case 6:
                return FLOOR;
            case 7:
                return TREE;
            case 8:
                return GRASS2;
            case 9:
                return DOOR;
            default:
                return EMPTY;
        }
    }
}