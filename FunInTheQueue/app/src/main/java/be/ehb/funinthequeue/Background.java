package be.ehb.funinthequeue;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Maarten on 15/12/15.
 */
public class Background {
    private Bitmap image;
    private int x, y;

    public Background(Bitmap mijnimage) {
        image = mijnimage;
    }
    public void update() {

    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        
    }
}
