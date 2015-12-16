package be.ehb.funinthequeue;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ToonLeemans on 16/12/15.
 */
public class setImages extends MainActivity {

    public void showImage(int image) {
        ImageView backgroundImage;
        switch(image){
            default:
            case 0:
                backgroundImage = (ImageView) findViewById(R.id.backgroundGame);
                backgroundImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.backgroundGame, 1800, 2000));
                break;
            case 1:
                backgroundImage = (ImageView) findViewById(R.id.backgroundQueue);
                backgroundImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.backgroundQueue, 1, 1));
                break;
            case 2:
                backgroundImage = (ImageView) findViewById(R.id.backgroundHome);
                backgroundImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.backgroundHome, 100, 100));
                break;
            /*case 3:
                backgroundImage = (ImageView) findViewById(R.id.backgroundProfile);
                backgroundImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.backgroundProfile, 100, 100));
                break;*/
        }
    }
    public void goToGame(View view) {
        showImage(0);
    }
    public void goToQueue(View view) {
        showImage(1);
    }
    public void goToHome(View view) {
        showImage(2);
    }
    public void goToProfile(View view) {
        showImage(3);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
