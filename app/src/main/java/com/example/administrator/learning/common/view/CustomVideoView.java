package com.example.administrator.learning.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by Administrator on 2018/11/10 0010.
 */

public class CustomVideoView extends VideoView {
    int defaultwidth=1920;
    int defaultheight=1080;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(defaultwidth,widthMeasureSpec);
        int height = getDefaultSize(defaultheight,heightMeasureSpec);
        setMeasuredDimension(width,height);
    }
}
