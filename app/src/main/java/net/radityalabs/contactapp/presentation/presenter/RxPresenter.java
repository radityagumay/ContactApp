package net.radityalabs.contactapp.presentation.presenter;

import net.radityalabs.contactapp.presentation.rx.RxBus;
import net.radityalabs.contactapp.presentation.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by radityagumay on 4/12/17.
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeSubscription;

    protected void dispose() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

    protected void addDisposable(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(RxBus.getDefault().toDefaultObservable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        dispose();
    }
}
