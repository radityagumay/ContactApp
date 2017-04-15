package net.radityalabs.contactapp.presentation.util;

import android.content.Context;

/**
 * Created by radityagumay on 4/15/17.
 */

public class WidgetUtil {

    private static float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, int pixel) {
        return pixel / density(context);
    }

    public static float dpToPx(Context context, int dp) {
        return dp * density(context);
    }
}
