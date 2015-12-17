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
public class BekerOld extends Item {



    private int DeviceWidth;
    private Bitmap image;
    private int score;
    private double dya;
    private boolean press;
    private boolean playing;
    //private Animation animatie = new Animation();

    private long startTime;


    public BekerOld(Bitmap res, int w, int h, int Dwidth) {
        x = 100;
        y = (int) (GamePanel.heightBackground);
        DeviceWidth = Dwidth;

        dx = 0;
        score = 0;

        height = h;
        width = w;

        Log.d("draw","beker size " + width + " x " +  height);
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

        if (x > DeviceWidth) {
            x = DeviceWidth;


        }

        if (x < 0) {
            x = 0;

        }


    }
    public void draw(Canvas canvas) {
        //Log.d("draw","image size @drawing time" + image.getWidth() + " x " + image.getHeight());
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
