package net.radityalabs.contactapp.presentation.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by radityagumay on 4/13/17.
 */

public class RecycleViewUtil {

    public static LinearLayoutManager linearLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(true);
        return linearLayoutManager;
    }

    public static LinearLayoutManager simpleLinearLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        return linearLayoutManager;
    }
}
