package com.example.walker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

public class Player implements Updatable, Renderable {

    static float x, y;
    protected int padding = 2;
    float moveX, moveY;
    Paint paint;
    String direction;

    GameView gameView;

    public Player(GameView gameView) {
        this.gameView = gameView;
        x = (float) (gameView.getWidth() / 2);
        y = (float) (gameView.getHeight() / 2);
        direction = "STOP";
        moveX = 0;
        moveY = 0;

        paint = new Paint();

        Toast.makeText(gameView.getContext(),
                "width = " + gameView.getWidth() +
                        "height = " + gameView.getHeight(),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void render(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, GameView.TILE_SIZE, paint);

    }

    @Override
    public void update(float deltaTime) {

        x += moveX;
        y += moveY;

        Point[] array = getCornerPoints();

       /* for (Point p : array) {
           // collided = false;
            if (!TileMap.isPassable(p.x, p.y)) {
                x = x - moveX;
                y = y - moveY;
                moveX = 0;
                moveY = 0;
               // collidedPoint = p;
                break;
            }
        }*/
    }

    public Point[] getCornerPoints() {
        Point[] array = new Point[4];
        array[0] = new Point((int) x + padding,
                (int) y + padding);

        array[1] = new Point((int) x + GameView.TILE_SIZE - padding,
                (int) y + padding);

        array[2] = new Point((int) x + GameView.TILE_SIZE - padding,
                (int) y + GameView.TILE_SIZE - padding);

        array[3] = new Point((int) x + padding,
                (int) y + GameView.TILE_SIZE - padding);

        return array;
    }
}
