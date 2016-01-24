package xu.ferris.flymeshview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 实现更换壁纸，拉动窗帘的动画效果
 * 459821731@qq.com
 * Created by ferris on 2016/1/23.
 */
public class ChangeWallpaperView extends FrameLayout {
    private static final int DURATION_DEFAULT = 2000;
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

        ObjectAnimator animator = ObjectAnimator.ofFloat(viewActivity, "FolderX",0f,1f);
        animator.addListener(openAnimatorListener);
        animator.setDuration(mDuration);
        animator.start();

//        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(scrollViewMenu, "alpha", scrollViewMenu.getAlpha(), 1f);
//        alphaAnimator.setDuration(500);
//        alphaAnimator.start();
    }


    private void setScaleDirection(int direction) {

        viewActivity.setDirection(direction);
        scaleDirection = direction;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewActivity= (TouchDisableView) findViewById(R.id.mTouchDisableView);
        ImageView mContent=new ImageView(getContext());
        mContent.setImageResource(R.drawable.default_wallpaper);
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

    public int getScreenHeight() {
        return getMeasuredHeight();
    }

    public int getScreenWidth() {
        return getMeasuredWidth();
    }


    private Animator.AnimatorListener openAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            viewActivity.setTouchDisable(false);
            if (isOpened) {
//                showScrollViewMenu(scrollViewMenu);
//                if (menuListener != null)
//                    menuListener.openMenu();
            }

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            if (isOpened) {
                viewActivity.setTouchDisable(true);
                viewActivity.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else {
                viewActivity.setTouchDisable(false);
                viewActivity.setOnClickListener(null);

            }
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

}
