package be.ehb.funinthequeue;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Path;
        import android.graphics.RectF;
        import android.util.AttributeSet;
        import android.widget.ImageView;

//http://stackoverflow.com/questions/18229358/bitmap-in-imageview-with-rounded-corners
public class ImageClip extends ImageView {

    public static float radius = 110f;

    public ImageClip(Context context) {
        super(context);
    }

    public ImageClip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageClip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //float radius = 36.0f;
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}