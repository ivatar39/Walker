package com.example.walker;

import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.walker.GameView.tiles;

public class TileMap implements Renderable {

    public static final int LAND = 0;
    public static final int WALL = 1;
    public static final int WATER = 2;
    GameView gameView;
    Paint paint;

    public TileMap(GameView gameView) {
        this.gameView = gameView;
        paint = new Paint();
    }

    public static boolean isPassable(double x, double y) {
        int row = getRowByY(y);
        int col = getColByX(x);
        int type;
        try {
            if (row >= tiles.length || col >= tiles[0].length) return false;
            type = tiles[row][col].getType();
        } catch (Exception e) {
            return false;
        }

        return type == 1;
    }

    public static int getColByX(double x) {
        return (int) x / GameView.TILE_SIZE;
    }

    public static int getRowByY(double y) {
        return (int) y / GameView.TILE_SIZE;
    }

    public static int getWorldWidth() {
        return tiles[0].length * GameView.TILE_SIZE;
    }

    public static int getWorldHeight() {
        return tiles.length * GameView.TILE_SIZE;

    }

    @Override
    public void render(Canvas canvas) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];

                paint.setColor(tile.getColor());
                canvas.drawRect(
                        tile.getX(),
                        tile.getY(),
                        tile.getSize(),
                        tile.getSize(),
                        paint
                );
            }
        }
    }

}
