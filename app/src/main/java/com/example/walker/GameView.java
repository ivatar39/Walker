package com.example.walker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class GameView extends View {
    public static int score = 0;
    public static int TILE_SIZE = 96;
    public static int WIDTH;
    public static int HEIGHT;
    public static Tile[][] tiles;
    public long lastUpdate = -1;
    public float timeScale = 1f;
    Paint paint = new Paint();  //объект-художник
    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Object> objectAddBuffer = new ArrayList<>(10);
    private boolean generatedMap = false;

    public GameView(Context context) {
        super(context);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        long currentTime = System.currentTimeMillis();
        if (currentTime > lastUpdate) lastUpdate = currentTime;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!generatedMap) {
            init(getHeight(), getWidth());
            generatedMap = true;
        }


        long currentTime = System.currentTimeMillis();
        update((currentTime - lastUpdate) * 0.001f * timeScale);
        render(canvas);
        invalidate();
        lastUpdate = currentTime;
    }

    public void addObject(Object o) {
        objectAddBuffer.add(o);
    }

    public void removeObject(Object o) {
        objects.remove(o);
    }

    public void clear() {
        objects.clear();
    }

    public void render(Canvas canvas) {
        for (Object object : objects) {
            if (object instanceof Renderable)
                ((Renderable) object).render(canvas);
        }
    }

    public void update(float deltaTime) {
        for (int i = objects.size() - 1;
             i >= 0 && i < objects.size();
             i--) {

            final Object object = objects.get(i);
            if (object instanceof Updatable)
                ((Updatable) object).update(deltaTime);
        }
        if (!objectAddBuffer.isEmpty()) {
            objects.addAll(objectAddBuffer);
            objectAddBuffer.clear();
        }
    }

    private void init(int height, int width) {
        WIDTH = width / TILE_SIZE;
        HEIGHT = height / TILE_SIZE;

        tiles = new Tile[WIDTH][HEIGHT];
        fetchTiles();
        //fetchPoints();
    }

    private void fetchTiles() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(
                        (i * TILE_SIZE),
                        (j * TILE_SIZE),
                        Color.GREEN,
                        0
                );
            }
        }

        for (int i = 0; i < WIDTH; i++) {
            tiles[i][0].setType(1);
            tiles[i][0].setColor(Color.BLACK);
            tiles[i][WIDTH - 1].setType(1);
            tiles[i][WIDTH - 1].setColor(Color.BLACK);
        }

        /*for (int i = 0; i < HEIGHT; i++) {
            tiles[0][i].setType(1);
            tiles[0][i].setColor(Color.BLACK);
            tiles[HEIGHT-1][i].setType(1);
            tiles[HEIGHT-1][i].setColor(Color.BLACK);
        }*/
    }
}
/*private void fetchPoints() {
        Random r = new Random();
        for (int a = 0; a < WIDTH; a++) {
                Point point = new Point(
                        (a * TILE_SIZE) + TILE_SIZE / 2,
                        (r.nextInt(WIDTH) * TILE_SIZE) + TILE_SIZE / 2
                );
                points.add(point);
        }
}*/



