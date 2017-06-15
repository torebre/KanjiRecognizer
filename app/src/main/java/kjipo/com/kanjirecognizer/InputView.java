package kjipo.com.kanjirecognizer;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class InputView extends View {
    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private Canvas kanjiInput;
    private Bitmap kanjiBitmap;


    public InputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        drawPath = new Path();
        drawPaint = new Paint();

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        kanjiBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        kanjiInput = new Canvas(kanjiBitmap);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(kanjiBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                kanjiInput.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }


    int[] getKanjiBitmap() {
        int[] bitMap = new int[getWidth() * getHeight()];

        kanjiBitmap.getPixels(bitMap, 0, getWidth(), 0, 0, getWidth(), getHeight());

        return bitMap;
    }


    void clearInput() {
        kanjiInput.drawColor(0, PorterDuff.Mode.CLEAR);
    }


}
