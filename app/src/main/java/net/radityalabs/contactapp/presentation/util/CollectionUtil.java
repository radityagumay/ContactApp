package net.radityalabs.contactapp.presentation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radityagumay on 4/12/17.
 */

public class CollectionUtil {

    public static <T> List<T> join(List<T>... lists) {
        List<T> result = new ArrayList<T>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    public static <T> boolean isValid(List<T> collections) {
        return collections != null && collections.size() > 0;
    }
}
