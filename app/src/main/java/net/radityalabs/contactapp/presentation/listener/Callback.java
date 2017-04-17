package net.radityalabs.contactapp.presentation.listener;

/**
 * Created by radityagumay on 4/17/17.
 */

public interface Callback<T> {

    void onSuccess(T success);

    void onFailure(Throwable throwable);
}
