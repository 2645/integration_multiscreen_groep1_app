package be.ehb.funinthequeue;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by Maarten on 16/12/15.
 */
public class Blokje extends Item {
    private int score;
    private double speed;
    private int DeviceWidth;
    private Random rand = new Random();
    private Bitmap image;


    public Blokje(Bitmap res, int x, int y, int w, int h, int s, int DWidth) {

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;
        DeviceWidth = DWidth;

        speed = 10 + score/10;


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
            Log.d("score", String.valueOf(speed));
        }
        catch(Exception e) {

        }


    }
    @Override
    public int getWidth() {
        return width - 10;
    }
}
