package net.radityalabs.contactapp.presentation.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by radityagumay on 4/13/17.
 */

public class CircularAnimUtil {

    public static final long PERFECT_MILLS = 618;
    public static final int MINI_RADIUS = 0;

    @SuppressLint("NewApi")
    public static void show(View myView, float startRadius, long durationMills) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            myView.setVisibility(View.VISIBLE);
            return;
        }
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;
        int w = myView.getWidth();
        int h = myView.getHeight();
        int finalRadius = (int) Math.sqrt(w * w + h * h) + 1;
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, startRadius, finalRadius);
        myView.setVisibility(View.VISIBLE);
        anim.setDuration(durationMills);
        anim.start();
    }

    @SuppressLint("NewApi")
    public static void hide(final View myView, float endRadius, long durationMills) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            myView.setVisibility(View.INVISIBLE);
            return;
        }
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;
        int w = myView.getWidth();
        int h = myView.getHeight();
        int initialRadius = (int) Math.sqrt(w * w + h * h) + 1;
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, endRadius);
        anim.setDuration(durationMills);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }

    @SuppressLint("NewApi")
    public static void startActivityForResult(final Activity thisActivity, final Intent intent, final Integer requestCode, final Bundle bundle, final View triggerView, int colorOrImageRes, long durationMills) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            thisActivity.startActivity(intent);
            return;
        }
        int[] location = new int[2];
        triggerView.getLocationInWindow(location);
        final int cx = location[0] + triggerView.getWidth() / 2;
        final int cy = location[1] + triggerView.getHeight() / 2;
        final ImageView view = new ImageView(thisActivity);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(colorOrImageRes);
        final ViewGroup decorView = (ViewGroup) thisActivity.getWindow().getDecorView();
        int w = decorView.getWidth();
        int h = decorView.getHeight();
        decorView.addView(view, w, h);

        int maxW = Math.max(cx, w - cx);
        int maxH = Math.max(cy, h - cy);
        final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
        if (durationMills == PERFECT_MILLS) {
            double rate = 1d * finalRadius / maxRadius;
            durationMills = (long) (PERFECT_MILLS * rate);
        }
        final long finalDuration = durationMills;
        anim.setDuration(finalDuration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (requestCode == null)
                    thisActivity.startActivity(intent);
                else if (bundle == null)
                    thisActivity.startActivityForResult(intent, requestCode);
                else
                    thisActivity.startActivityForResult(intent, requestCode, bundle);
                thisActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                triggerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                        anim.setDuration(finalDuration);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                try {
                                    decorView.removeView(view);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        anim.start();
                    }
                }, 1000);

            }
        });
        anim.start();
    }

    public static void startActivityForResult(Activity thisActivity, Intent intent, Integer requestCode, View triggerView, int colorOrImageRes) {
        startActivityForResult(thisActivity, intent, requestCode, null, triggerView, colorOrImageRes, PERFECT_MILLS);
    }

    public static void startActivity(Activity thisActivity, Intent intent, View triggerView, int colorOrImageRes, long durationMills) {
        startActivityForResult(thisActivity, intent, null, null, triggerView, colorOrImageRes, durationMills);
    }

    public static void startActivity(Activity thisActivity, Intent intent, View triggerView, int colorOrImageRes) {
        startActivity(thisActivity, intent, triggerView, colorOrImageRes, PERFECT_MILLS);
    }

    public static void startActivity(Activity thisActivity, Class<?> targetClass, View triggerView, int colorOrImageRes) {
        startActivity(thisActivity, new Intent(thisActivity, targetClass), triggerView, colorOrImageRes, PERFECT_MILLS);
    }

    public static void show(View myView) {
        show(myView, MINI_RADIUS, PERFECT_MILLS);
    }

    public static void hide(View myView) {
        hide(myView, MINI_RADIUS, PERFECT_MILLS);
    }

}
