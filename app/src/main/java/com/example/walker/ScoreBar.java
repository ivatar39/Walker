package com.example.walker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreBar implements Renderable, Updatable {

    GameView gameView;
    Paint paint;

    public ScoreBar(GameView gameView) {
        this.gameView = gameView;
        paint = new Paint();
    }

    @Override
    public void render(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, GameView.TILE_SIZE * 3, GameView.TILE_SIZE, paint);
        paint.setColor(Color.RED);
        canvas.drawText(String.valueOf(GameView.score), 10, 10, paint);
    }

    @Override
    public void update(float deltaTime) {
        Canvas canvas = new Canvas();
        canvas.drawText(String.valueOf(GameView.score), 10, 10, paint);
    }
}
