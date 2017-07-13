package net.radityalabs.contactapp.presentation.rx

import net.radityalabs.contactapp.presentation.presenter.BasePresenter
import net.radityalabs.contactapp.presentation.rx.RxBus
import net.radityalabs.contactapp.presentation.view.BaseView

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by radityagumay on 4/12/17.
 */

open class RxPresenter<T : BaseView> : BasePresenter<T> {

    protected var mView: T? = null
    protected var mCompositeSubscription: CompositeDisposable? = null

    protected fun dispose() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription!!.clear()
        }
    }

    protected fun addDisposable(subscription: Disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeDisposable()
        }
        mCompositeSubscription!!.add(subscription)
    }

    protected fun <U> addRxBusSubscribe(eventType: Class<U>, act: Consumer<U>) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeDisposable()
        }
        mCompositeSubscription!!.add(RxBus.default.toDefaultObservable(eventType, act))
    }

    override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
        dispose()
    }
}
