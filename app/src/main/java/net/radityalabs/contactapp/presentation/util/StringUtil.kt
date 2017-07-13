package net.radityalabs.contactapp.presentation.util

import android.text.TextUtils

/**
 * Created by radityagumay on 4/12/17.
 */

object StringUtil {

    fun getFirstChar(firstName: String): String {
        if (!TextUtils.isEmpty(firstName)) {
            return firstName.substring(0, firstName.length - (firstName.length - 1))
        }
        return "A"
    }

    fun mergeString(vararg str: String): String {
        val builder = StringBuilder()
        for (aStr in str) {
            builder.append(aStr).append(" ")
        }
        return builder.toString()
    }

    fun isValidName(str: String): Boolean {
        return str.length < 7 || str.matches(".*([ \t]).*".toRegex())
    }
}
