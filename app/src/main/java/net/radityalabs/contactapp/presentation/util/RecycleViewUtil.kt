package net.radityalabs.contactapp.presentation.util

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * Created by radityagumay on 4/13/17.
 */

object RecycleViewUtil {

    fun linearLayoutManager(context: Context): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true
        return linearLayoutManager
    }

    fun simpleLinearLayoutManager(context: Context): LinearLayoutManager {
        val linearLayoutManager = LinearLayoutManager(context)
        return linearLayoutManager
    }
}
