package com.ryx.widget.bubble;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ryx.widget.R;


/**
 * GooViewListener
 * Created by ryx on 2020/1/21.
 * 贝塞尔曲线监听器
 */
public class GooViewListener implements OnTouchListener, GooView.OnDisappearListener {

    private WindowManager mWm;
    private WindowManager.LayoutParams mParams;
    private GooView mGooView;
    private View pointLayout;
    private int number;
    private final Context mContext;

    private int position;
    private Handler mHandler;

    public GooViewListener(Context mContext, View pointLayout, int position) {
        this.mContext = mContext;
        this.pointLayout = pointLayout;
        this.position = position;
        this.number = (Integer) pointLayout.getTag();

        mGooView = new GooView(mContext);
        mGooView.setPosition(position);

        mWm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.format = PixelFormat.TRANSLUCENT;//使窗口支持透明度

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }

        mHandler = new Handler(mContext.getMainLooper());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        // 当按下时，将自定义View添加到WindowManager中

        if (action == MotionEvent.ACTION_DOWN) {
            ViewParent parent = v.getParent();
            // 请求其父级View不拦截Touch事件
            parent.requestDisallowInterceptTouchEvent(true);
            int[] points = new int[2];
            //获取pointLayout在屏幕中的位置（layout的左上角坐标）
            pointLayout.getLocationInWindow(points);
            //获取初始小红点中心坐标
            int x = points[0] + pointLayout.getWidth() / 2;
            int y = points[1] + pointLayout.getHeight() / 2;
            // 初始化当前点击的item的信息，数字及坐标
            mGooView.setStatusBarHeight(getStatusBarHeight(v));
            mGooView.setNumber(number);
            mGooView.initCenter(x, y);
            //设置当前GooView消失监听
            mGooView.setOnDisappearListener(this);
            // 添加当前GooView到WindowManager
            mWm.addView(mGooView, mParams);
            pointLayout.setVisibility(View.INVISIBLE);
        }

        // 将所有touch事件转交给GooView处理
        mGooView.onTouchEvent(event);
        return true;
    }

    @Override
    public void onDisappear(PointF mDragCenter) {
        if (mWm != null && mGooView.getParent() != null) {
            mWm.removeView(mGooView);

            //播放气泡爆炸动画
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.anim_bubble_pop);
            AnimationDrawable mAnimDrawable = (AnimationDrawable) imageView
                    .getDrawable();

            final GooViewAnimLayout bubbleLayout = new GooViewAnimLayout(mContext);
            bubbleLayout.setCenter((int) mDragCenter.x, (int) mDragCenter.y - getStatusBarHeight(mGooView));

            bubbleLayout.addView(imageView, new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));

            mWm.addView(bubbleLayout, mParams);

            mAnimDrawable.start();

            // 播放结束后，删除该bubbleLayout
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWm.removeView(bubbleLayout);
                }
            }, 500);
        }

    }

    @Override
    public void onReset(boolean isOutOfRange) {
        // 当dragPoint弹回时，去除该View，等下次ACTION_DOWN的时候再添加
        if (mWm != null && mGooView.getParent() != null) {
            mWm.removeView(mGooView);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
}
