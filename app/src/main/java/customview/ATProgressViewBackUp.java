package customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atloginbutton.R;

/**
 * Created by jsion on 16/7/29.
 */

public class ATProgressViewBackUp extends View {

    private static final float OUTER_LAYER_LARGE_SCALE = 58 / 62.F;
    private static final float MIDDLE_LAYER_LARGE_SCALE = 51 / 62.F;
    private static final float SHADOW_LAYER_LARGE_SCALE = 44 / 62.F;
    private int outLayerSolideColor;
    private int outLayerStrokeColor;
    private int outLayerStrokeWidth;
    private int midlleLayerProgressColor;
    private int midlleLayerProgressWidth;
    private int smallCircleSolideColor;
    private int smallCircleStrokeColor;
    private int smallCircleSize;
    private int smallCircleStrokeWidth;
    private int shadowLayerColor;
    private int shadowLayerInnerColor;
    private int innerTextSize;
    private int innerTextColor;


    private int viewWidth;
    private int viewHeight;

    private Paint progressBgPaint;
    private Paint progressPaint;
    private Paint smallCirclePaint;
    private Paint smallCircleInnerPaint;

    public ATProgressViewBackUp(Context context) {
        this(context, null);
    }

    public ATProgressViewBackUp(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ATProgressViewBackUp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
    }

    private void initData() {
        progressBgPaint = creatPaint(getResources().getColor(R.color.dashLineColor), 0, Paint.Style.STROKE, 10);
        progressPaint = creatPaint(getResources().getColor(R.color.colorAccent), 0, Paint.Style.STROKE, 10);
        smallCirclePaint = creatPaint(getResources().getColor(R.color.textHighLightColor), 0, Paint.Style.FILL, 0);
        smallCircleInnerPaint = creatPaint(getResources().getColor(R.color.colorW), 0, Paint.Style.FILL, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = viewWidth / 2;
        //外切圆的坐标计算,绘制的扇形在矩形内的外切圆,注意画笔的宽度
        RectF oval = new RectF(10, 10, viewWidth - 10, viewHeight - 10);
        canvas.drawCircle(i, i, i - 10, progressBgPaint);
        canvas.drawArc(oval, 270, 77, false, progressPaint);

        // 由于前面绘制了一个小圆,所以我们弧度的角度不能用于计算圆的坐标,我们需要大概的加上那么一两度来计算
        float hudu = (float) Math.abs(Math.PI * 78 / 180);

        float sinAX = (float) Math.abs(Math.sin(hudu) * i);
        float cosAY = (float) Math.abs(Math.cos(hudu) * i);

        float xiaoYuanX = (viewWidth - 20 - 2 * sinAX) / 2 + 2 * sinAX;
        float xiaoYuanY = i + 10 - cosAY;

        canvas.drawCircle(xiaoYuanX, xiaoYuanY, 11, smallCirclePaint);
        canvas.drawCircle(xiaoYuanX, xiaoYuanY, 7, smallCircleInnerPaint);


    }

    /**
     * 初始化画笔
     *
     * @param paintColor 画笔颜色
     * @param textSize   文字大小
     * @param style      画笔风格
     * @param lineWidth  画笔宽度
     * @return 画笔
     */
    private Paint creatPaint(int paintColor, int textSize, Paint.Style style, int lineWidth) {
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(lineWidth);
        paint.setDither(true);
        paint.setTextSize(textSize);
        paint.setStyle(style);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }

}
