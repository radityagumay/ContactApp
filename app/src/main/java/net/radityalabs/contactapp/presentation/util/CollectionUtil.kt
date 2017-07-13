package net.radityalabs.contactapp.presentation.util

import java.util.ArrayList

/**
 * Created by radityagumay on 4/12/17.
 */

object CollectionUtil {

    fun <T> join(vararg lists: List<T>): List<T> {
        val result = ArrayList<T>()
        for (list in lists) {
            result.addAll(list)
        }
        return result
    }

    fun <T> isValid(collections: List<T>?): Boolean {
        return collections != null && collections.size > 0
    }
}
