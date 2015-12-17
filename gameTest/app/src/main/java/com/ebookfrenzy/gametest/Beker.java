package com.ebookfrenzy.gametest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;

/**
 * Created by Maarten on 16/12/15.
 */
public class Beker extends Item {




    private Bitmap image;
    private int score;
    private double dya;
    private boolean press;
    private boolean playing;
    //private Animation animatie = new Animation();

    private long startTime;


    public Beker(Bitmap res, int w, int h, int numFrames) {
        x = 100;
        y = (int) (GamePanel.heightBackground/2);


        dx = 0;
        score = 0;

        height = h;
        width = w;

        image = Bitmap.createBitmap(res, 0, 0, width, height);


        //animatie.setFrames(deBeker);
       // animatie.setDelay(10);

        startTime = System.nanoTime();





    }
    public void setup(boolean b) {
        press = b;
    }
    public void update() {
        //animatie.update();

        if (press) {
            dx = (int) (dya+=1);

        }
        else {
            dx = (int) (dya-=1);
        }


        x += dx;
        dx = 0;

        // randen

        if (x > 700) {
            x = getWidth();

            Log.d("width", String.valueOf(getWidth()));
        }
        if (x < 0) {
            x = 0;
        }


    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
    public void setPress(boolean b) {
        press = b;
    }
    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }
    public void resetDya() {
        dya = 0;
    }



}
