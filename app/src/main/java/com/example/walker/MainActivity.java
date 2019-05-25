package com.example.walker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements Updatable {
    public static Player player;
    public static TileMap tileMap;
    public GameView gameView;
    float timeElapsed = 0;

    public static void move(int x, int y) {
        player.moveX = x;
        player.moveY = y;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);

        setContentView(R.layout.activity_main);

        FrameLayout rootLayout = findViewById(R.id.frame1);
        rootLayout.addView(gameView);

        rootLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                move(0, -10);
            }

            public void onSwipeRight() {
                move(10, 0);
            }

            public void onSwipeLeft() {
                move(-10, 0);
            }

            public void onSwipeBottom() {
                move(0, 10);
            }

        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        gameView.addObject(this);
        player = new Player(gameView);
        tileMap = new TileMap(gameView);

        gameView.addObject(tileMap);
        gameView.addObject(player);
    }

    @Override
    public void update(float deltaTime) {
        if (timeElapsed > 1) {
            timeElapsed = 0;
        } else {
            timeElapsed += deltaTime;
        }
    }
}
