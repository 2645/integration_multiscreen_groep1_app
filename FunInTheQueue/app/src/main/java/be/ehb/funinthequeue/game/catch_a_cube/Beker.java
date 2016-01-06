package be.ehb.funinthequeue.game.catch_a_cube;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

/**
 * Created by Maarten on 16/12/15.
 */
public class Beker extends Item {
    private int score;
    private boolean press;
    private double dya;
    private int DeviceWidth;
    private Random rand = new Random();
    private Bitmap image;
    private boolean playing;


    public Beker(Bitmap res, int x, int y, int w, int h, int s, int DWidth) {

        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;
        DeviceWidth = DWidth;





        image = res;
    }
    public void update() {
        if (press) {
            dx = (int) (dya+=1);

        }
        else {
            dx = (int) (dya -= 1);
        }


        x += dx;
        dx = 0;

        // randen

        if (x > DeviceWidth- 10) {
            x = DeviceWidth-10;
            dya = 0;



        }

        if (x < (-width *2 + 20)) {
            x = (-width *2 + 20);
            dya = 0;
        }

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
    public void setPress(boolean b) {
        press = b;
    }
    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }
}
