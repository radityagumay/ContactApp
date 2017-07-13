package net.radityalabs.contactapp.presentation.rx.transformation

import net.radityalabs.contactapp.presentation.ui.foundation.Dialogs

import org.reactivestreams.Publisher
import org.reactivestreams.Subscription

import javax.inject.Inject

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Created by radityagumay on 4/12/17.
 */

class TransformationsBehaviour @Inject
constructor(private val dialog: Dialogs) : Transformation {

    override fun <T> loading(): FlowableTransformer<T, T> {
        return transforamation(null)
    }

    override fun <T> loading(message: String): FlowableTransformer<T, T> {
        return transforamation(message)
    }

    private fun <T> transforamation(message: String?): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream
                    .doOnSubscribe { dialog.showNoCancelableLoading(message ?: "Menunggu") }
                    .doOnComplete { dialog.hideLoading() }
                    .doOnError { dialog.hideLoading() }
        }
    }
}
