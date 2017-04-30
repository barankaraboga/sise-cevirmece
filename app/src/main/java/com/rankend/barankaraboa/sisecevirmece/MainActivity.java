package com.rankend.barankaraboa.sisecevirmece;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener,Runnable{
    private ImageView wheel;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    ImageView bask;
    private Thread t1;
    RotateAnimation animRotate;
    AnimationSet animSet;
    RotateAnimation rAnim;
    ImageView img;
    int derece = 0 ;
    int dereceSon = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
img = (ImageView) findViewById(R.id.imageView);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setFillAfter(true);
        animSet.setFillEnabled(true);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                derece = (int)(Math.random()*721);

                final RotateAnimation animRotate = new RotateAnimation(Integer.valueOf(String.valueOf(dereceSon),16),Integer.valueOf(String.valueOf(derece),16),
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                animRotate.setDuration(1500);
                animRotate.setFillAfter(true);
                animSet.addAnimation(animRotate);
                animSet.setRepeatCount(-1);
                img.startAnimation(animRotate);
                dereceSon = derece;
                if (animRotate.hasEnded()) {
                    img.startAnimation(animRotate);
                    derece = 0;
                }
            }
        });


        //at timer

        //t1=new Thread(this);
        //wheel=(ImageView)findViewById(R.id.imageView);
        //wheel.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = wheel.getWidth() / 2;
        final float yc = wheel.getHeight() / 2;

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                wheel.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP : {
                mPrevAngle = mCurrAngle = 0;
                break;
            }
        }
        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        wheel.startAnimation(rotate);

        System.out.println(mCurrAngle);
    }

    @Override
    public void run() {
        while(true)
        {

        }
    }
}