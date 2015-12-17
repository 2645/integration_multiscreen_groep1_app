package com.ebookfrenzy.gametest;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Maarten on 15/12/15.
 */
public class NewTread extends Thread {

    private int fps = 30;
    private double avgfps;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;

    public static Canvas canvas;


    public NewTread(SurfaceHolder SurfaceHolder, GamePanel gamePanel ) {
        super();
        this.surfaceHolder = SurfaceHolder;
        this.gamePanel = gamePanel;
    }
    @Override
    public void run() {
        long startTime;
        long timeMilis;
        long waitTime;
        long totaltime = 0;
        int framecount = 0;

        long targetTime = 1000 / fps;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            } catch (Exception e) {

            }
            finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {}
                }
            }


            timeMilis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMilis;

            try {
                this.sleep(waitTime);
            }
            catch (Exception e) {

            }
            totaltime += System.nanoTime() - startTime;
            framecount ++;


            if (framecount == fps) {
                avgfps = 1000 / ((totaltime/framecount) / 1000000);
                framecount = 0;
                totaltime  = 0;

                System.out.print(avgfps);
                Log.d("fps", String.valueOf(avgfps));



            }
        }
    }
    public void setrunning(boolean f) {

        running = f;

    }
}
