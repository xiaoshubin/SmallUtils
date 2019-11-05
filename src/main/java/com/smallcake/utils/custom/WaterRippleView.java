package com.smallcake.utils.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * MyApplication --  com.smallcake.utils.custom
 * Created by Small Cake on  2017/12/12 16:52.
 * 水波纹
 * 1.onMeasure():最先回调，用于控件的测量;
 2.onSizeChanged():在onMeasure后面回调，可以拿到view的宽高等数据，在横竖屏切换时也会回调;
 3.onDraw()：真正的绘制部分，绘制的代码都写到这里面;
 */

public class WaterRippleView extends View {

    int width,height;//宽高
    float mYPositions[];//所有y点集合

    int STRETCH_FACTOR_A;//延伸因子
    float OFFSET_Y;//退出时坐标

    public WaterRippleView(Context context) {
        super(context);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        init();
    }

    /**
     * 1.确定水波函数方程
     * y = Asin(wx+b)+h ，这个公式里：w影响周期，A影响振幅，h影响y位置，b为初相；
     */

    private void init() {
        // 将周期定为view总宽度
        float mCycleFactorW = (float) (2 * Math.PI / width);
        // 根据view总宽度得出所有对应的y值
        mYPositions = new float[width];
        for (int i = 0; i < width; i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
