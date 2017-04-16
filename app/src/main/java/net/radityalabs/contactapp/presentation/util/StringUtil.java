package net.radityalabs.contactapp.presentation.util;

import android.text.TextUtils;

/**
 * Created by radityagumay on 4/12/17.
 */

public class StringUtil {

    public static String getFirstChar(String firstName) {
        if (!TextUtils.isEmpty(firstName)) {
            return firstName.substring(0, firstName.length() - (firstName.length() - 1));
        }
        return "A";
    }

    public static String mergeString(String... str) {
        StringBuilder builder = new StringBuilder();
        for (String aStr : str) {
            builder.append(aStr).append(" ");
        }
        return builder.toString();
    }

    public static boolean isValidName(String str) {
        return str.length() < 7 || str.matches(".*([ \t]).*");
    }
}
