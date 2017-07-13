package net.radityalabs.contactapp.presentation.rx.transformation

import io.reactivex.FlowableTransformer

/**
 * Created by radityagumay on 4/12/17.
 */

interface Transformation {

    fun <T> loading(): FlowableTransformer<T, T>

    fun <T> loading(message: String): FlowableTransformer<T, T>
}
