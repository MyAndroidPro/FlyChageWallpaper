package xu.ferris.flymeshview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 实现更换壁纸，拉动窗帘的动画效果
 * 459821731@qq.com
 * Created by ferris on 2016/1/23.
 */
public class ChangeWallpaperView extends FrameLayout {
    private static final int DURATION_DEFAULT = 2500;
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    private TouchDisableView viewActivity;
    private int scaleDirection = DIRECTION_LEFT;
    private boolean isOpened;
    private int mDuration = DURATION_DEFAULT;
    public ChangeWallpaperView(Context context) {
        super(context);
    }

    public ChangeWallpaperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeWallpaperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    /**
     * Show the menu;
     */
    public void openMenu(int direction) {
        setScaleDirection(direction);
        isOpened = true;
        viewActivity.animate().alpha(1f).setDuration(1500).setStartDelay(1000).start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewActivity, "FolderX",0f,1f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                closeMenu(DIRECTION_LEFT);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(mDuration);
        animator.start();
    }


    /**
     * Show the menu;
     */
    public void closeMenu(int direction) {
        setScaleDirection(direction);
        isOpened = true;
        viewActivity.animate().alpha(0f).setDuration(500).setStartDelay(1000).start();
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewActivity, "FolderX",1f,0f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ((Activity)getContext()).finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(mDuration);
        animator.start();
    }
    private void setScaleDirection(int direction) {

        viewActivity.setDirection(direction);
        scaleDirection = direction;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewActivity= (TouchDisableView) findViewById(R.id.mTouchDisableView);
        viewActivity.setAlpha(0f);
        ImageView mContent=new ImageView(getContext());
        mContent.setImageResource(R.drawable.oneclcik_wallpaper_bg);
        mContent.setScaleType(ImageView.ScaleType.FIT_XY);
        ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.setLayoutParams(lp);
        viewActivity.setContent(mContent);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                openMenu(DIRECTION_RIGHT);
            }
        },500);
    }





}
