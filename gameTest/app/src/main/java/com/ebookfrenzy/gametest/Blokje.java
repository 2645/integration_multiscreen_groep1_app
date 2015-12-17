package com.ebookfrenzy.gametest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by Maarten on 16/12/15.
 */
public class Blokje extends Item {
    private int score;
    private int speed;
    private Random rand = new Random();
    private Bitmap image;


    public Blokje(Bitmap res, int x, int y, int w, int h, int s, int aantalframes) {

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;

        speed = 10 + (int) rand.nextDouble()* score/30;

        if (speed > 30) {
            speed = 30;
        }
        image = res;
    }
    public void update() {
        y += speed;
    }
    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(image, x, y, null);
        }
        catch(Exception e) {

        }


    }
    @Override
    public int getWidth() {
        return width - 10;
    }
}
