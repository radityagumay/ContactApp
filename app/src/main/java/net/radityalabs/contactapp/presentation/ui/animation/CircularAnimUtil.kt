package net.radityalabs.contactapp.presentation.ui.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by radityagumay on 4/13/17.
 */

object CircularAnimUtil {

    val PERFECT_MILLS: Long = 618
    val MINI_RADIUS = 0

    @SuppressLint("NewApi")
    fun show(myView: View, startRadius: Float, durationMills: Long) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            myView.visibility = View.VISIBLE
            return
        }
        val cx = (myView.left + myView.right) / 2
        val cy = (myView.top + myView.bottom) / 2
        val w = myView.width
        val h = myView.height
        val finalRadius = Math.sqrt((w * w + h * h).toDouble()).toInt() + 1
        val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, startRadius, finalRadius.toFloat())
        myView.visibility = View.VISIBLE
        anim.duration = durationMills
        anim.start()
    }

    @SuppressLint("NewApi")
    fun hide(myView: View, endRadius: Float, durationMills: Long) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            myView.visibility = View.INVISIBLE
            return
        }
        val cx = (myView.left + myView.right) / 2
        val cy = (myView.top + myView.bottom) / 2
        val w = myView.width
        val h = myView.height
        val initialRadius = Math.sqrt((w * w + h * h).toDouble()).toInt() + 1
        val anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius.toFloat(), endRadius)
        anim.duration = durationMills
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                myView.visibility = View.INVISIBLE
            }
        })

        anim.start()
    }

    @SuppressLint("NewApi")
    fun startActivityForResult(thisActivity: Activity, intent: Intent, requestCode: Int?, bundle: Bundle?, triggerView: View, colorOrImageRes: Int, durationMills: Long) {
        var durationMills = durationMills
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            thisActivity.startActivity(intent)
            return
        }
        val location = IntArray(2)
        triggerView.getLocationInWindow(location)
        val cx = location[0] + triggerView.width / 2
        val cy = location[1] + triggerView.height / 2
        val view = ImageView(thisActivity)
        view.scaleType = ImageView.ScaleType.CENTER_CROP
        view.setImageResource(colorOrImageRes)
        val decorView = thisActivity.window.decorView as ViewGroup
        val w = decorView.width
        val h = decorView.height
        decorView.addView(view, w, h)

        val maxW = Math.max(cx, w - cx)
        val maxH = Math.max(cy, h - cy)
        val finalRadius = Math.sqrt((maxW * maxW + maxH * maxH).toDouble()).toInt() + 1
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        val maxRadius = Math.sqrt((w * w + h * h).toDouble()).toInt() + 1
        if (durationMills == PERFECT_MILLS) {
            val rate = 1.0 * finalRadius / maxRadius
            durationMills = (PERFECT_MILLS * rate).toLong()
        }
        val finalDuration = durationMills
        anim.duration = finalDuration
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (requestCode == null)
                    thisActivity.startActivity(intent)
                else if (bundle == null)
                    thisActivity.startActivityForResult(intent, requestCode)
                else
                    thisActivity.startActivityForResult(intent, requestCode, bundle)
                thisActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                triggerView.postDelayed({
                    val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius.toFloat(), 0f)
                    anim.duration = finalDuration
                    anim.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            try {
                                decorView.removeView(view)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }

                        }
                    })
                    anim.start()
                }, 1000)

            }
        })
        anim.start()
    }

    fun startActivityForResult(thisActivity: Activity, intent: Intent, requestCode: Int?, triggerView: View, colorOrImageRes: Int) {
        startActivityForResult(thisActivity, intent, requestCode, null, triggerView, colorOrImageRes, PERFECT_MILLS)
    }

    @JvmOverloads fun startActivity(thisActivity: Activity, intent: Intent, triggerView: View, colorOrImageRes: Int, durationMills: Long = PERFECT_MILLS) {
        startActivityForResult(thisActivity, intent, null, null, triggerView, colorOrImageRes, durationMills)
    }

    fun startActivity(thisActivity: Activity, targetClass: Class<*>, triggerView: View, colorOrImageRes: Int) {
        startActivity(thisActivity, Intent(thisActivity, targetClass), triggerView, colorOrImageRes, PERFECT_MILLS)
    }

    fun show(myView: View) {
        show(myView, MINI_RADIUS.toFloat(), PERFECT_MILLS)
    }

    fun hide(myView: View) {
        hide(myView, MINI_RADIUS.toFloat(), PERFECT_MILLS)
    }

}
