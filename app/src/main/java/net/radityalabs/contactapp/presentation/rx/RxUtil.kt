package net.radityalabs.contactapp.presentation.rx

import android.content.Context
import android.view.View

import net.radityalabs.contactapp.presentation.factory.SnackbarFactory
import net.radityalabs.contactapp.presentation.factory.ToastFactory

import org.reactivestreams.Publisher

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.FlowableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Created by radityagumay on 4/12/17.
 */

object RxUtil {

    fun <T> showToast(context: Context, message: String): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream -> upstream.doOnError { ToastFactory.show(context, message) }.onErrorResumeNext(Function<Throwable, Publisher<out T>> { Flowable.empty<out T>() }) }
    }

    fun <T> showSnackbar(view: View, message: String): SingleTransformer<T, T> {
        return SingleTransformer { upstream -> upstream.doOnError { SnackbarFactory.show(view, message) } }
    }

    fun <T> flowableNewThread(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> singleIo(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> singleNewThread(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> singleDoOnSubscribe(context: Context): SingleTransformer<T, T> {
        return SingleTransformer { upstream -> upstream.doOnSubscribe { ToastFactory.show(context, "No Internet Connection") } }
    }

    fun <T> rxIo(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> buildResponse(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }
}
