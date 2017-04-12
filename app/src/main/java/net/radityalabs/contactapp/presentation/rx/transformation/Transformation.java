package net.radityalabs.contactapp.presentation.rx.transformation;

import io.reactivex.FlowableTransformer;

/**
 * Created by radityagumay on 4/12/17.
 */

public interface Transformation {

    <T> FlowableTransformer<T, T> loading();

    <T> FlowableTransformer<T, T> loading(String message);
}
