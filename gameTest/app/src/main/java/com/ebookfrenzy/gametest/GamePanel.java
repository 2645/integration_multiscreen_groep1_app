package com.ebookfrenzy.gametest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Maarten on 15/12/15.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public int DeviceW;

    public static final float widthBackground = 1080;
    public static final float heightBackground = 1920;
    public int mijnscore = 1;
    private Random rand = new Random();

    private NewTread Thread;
    private Background mijnBackground;
    private Beker mijnBeker;




    private long snelheid;

    private ArrayList<Blokje> blokjesLijst;

    public GamePanel(Context context,int Dwidth) {
        super(context);
        DeviceW = Dwidth;
        getHolder().addCallback(this);

        Thread = new NewTread(getHolder(), this);
        setFocusable(true);

    }
    @Override

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override

    public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            while(retry) {
                try {
                    Thread.setrunning(false);
                    Thread.join();
                }
                catch(InterruptedException e) {
                    System.out.print(e.getStackTrace());

                }
                retry = false;
            }

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            mijnBackground = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background02));
            //mijnBeker = new Beker(BitmapFactory.decodeResource(getResources(), R.drawable.beker03), 160, 230, DeviceW);
            mijnBeker = new Beker(BitmapFactory.decodeResource(getResources(),R.drawable.beker04),
                100, 800,  100, 144, mijnscore, DeviceW);
            blokjesLijst = new ArrayList<Blokje>();
            snelheid = 1;

            Thread.setrunning(true);
            Thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) { // press down

            if (!mijnBeker.getPlaying()) {
                mijnBeker.setPlaying(true);
            }
            else {
                mijnBeker.setPress(true);
            }

            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mijnBeker.setPress(false);
            return true;
        }


        return super.onTouchEvent(event);
    }
    public void update() {
        snelheid += 1;

        if (mijnBeker.getPlaying()) {

            mijnBackground.update();
            mijnBeker.update();


            int moeilijkheid = mijnscore;



            if (snelheid % 50  == 0) {

                blokjesLijst.add(new Blokje(BitmapFactory.decodeResource(getResources(),R.drawable.blokje02),
                        (int)(rand.nextDouble()*(widthBackground)), 0,  48, 52, mijnscore, 0));


            }




            for(int i = 0; i < blokjesLijst.size(); i++) {
                blokjesLijst.get(i).update();


                // blokje in beker
                if (collision(blokjesLijst.get(i), mijnBeker)) {
                    blokjesLijst.remove(i);
                    mijnscore += 1;
                }

                // blokje uit het scherm
                if (blokjesLijst.get(i).getX() < - 300) {
                    blokjesLijst.remove(i);
                }


            }
        }
    }
    @Override
    public void draw(Canvas canvas) {

       final float scaleX = getWidth()/widthBackground;
        final float scaleY = getWidth()/heightBackground;



        float scale1 = (float) (0.5);
        float scale2 = (float) (0.5);


        super.draw(canvas);

        if (canvas != null) {
            Log.d("scale", String.valueOf(scaleY));
            final int savedState = canvas.save();

            canvas.scale(scaleX, scaleY);

            mijnBackground.draw(canvas);
            canvas.restoreToCount(savedState);


            drawText(canvas);

            mijnBeker.draw(canvas);

            for (int i = 0; i < blokjesLijst.size(); i++) {
                blokjesLijst.get(i).draw(canvas);
            }

        }

    }

    public boolean collision(Item blokje, Item player) {
        /*if (Rect.intersects(blokje.getRectangle(), player.getRectangle())) {
            return true;
        }
        else {
            return false;
        }
        */

        if (blokje.y + 100> player.y && blokje.y + 50 < player.y ) {
            if (blokje.x > player.x - 90 && blokje.x < (player.x + player.width + 90)) {
                return true;
            }

        }

        return false;

    }
    public void drawText(Canvas canvas) {
        Paint mijnpaint = new Paint();
        mijnpaint.setColor(Color.WHITE);
        mijnpaint.setTextSize(30);
        mijnpaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        canvas.drawText("Blokjes opgevangen: " + mijnscore, 10, getHeight() - 10, mijnpaint);
    }


}
