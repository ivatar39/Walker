package com.example.walker;

public class Tile {
    private int x;
    private int y;
    private int color;
    private int size;
    private int type;

    Tile(int x, int y, int color, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
        size = GameView.TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
