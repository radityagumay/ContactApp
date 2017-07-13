package net.radityalabs.contactapp.presentation.ui.activity

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout

import net.radityalabs.contactapp.R

/**
 * Created by radityagumay on 4/19/17.
 */

@VisibleForTesting
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frameLayout = FrameLayout(this)
        frameLayout.id = R.id.container
        setContentView(frameLayout)
    }
}