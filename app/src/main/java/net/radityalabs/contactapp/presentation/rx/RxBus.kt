package net.radityalabs.contactapp.presentation.rx

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor

/**
 * Created by radityagumay on 4/12/17.
 */

class RxBus private constructor() {
    private val bus: PublishProcessor<Any>

    init {
        bus = PublishProcessor.create<Any>()
    }

    private object RxBusHolder {
        private val sInstance = RxBus()
    }

    fun post(o: Any) {
        bus.onNext(o)
    }

    fun <T> toObservable(eventType: Class<T>): Flowable<T> {
        return bus.ofType(eventType)
    }

    fun <T> toDefaultObservable(eventType: Class<T>, act: Consumer<T>): Disposable {
        return bus.ofType(eventType).compose(RxUtil.flowableNewThread<T>()).subscribe(act)
    }

    companion object {

        val default: RxBus
            get() = RxBusHolder.sInstance
    }
}
