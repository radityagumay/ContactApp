package net.radityalabs.contactapp.presentation.rx;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by radityagumay on 4/12/17.
 */

public class RxBus {
    private final PublishProcessor<Object> bus;

    private RxBus() {
        bus = PublishProcessor.create();
    }

    private static class RxBusHolder {
        private static final RxBus sInstance = new RxBus();
    }

    public static RxBus getDefault() {
        return RxBusHolder.sInstance;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public <T> Flowable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    public <T> Disposable toDefaultObservable(Class<T> eventType, Consumer<T> act) {
        return bus.ofType(eventType).compose(RxUtil.<T>flowableNewThread()).subscribe(act);
    }
}
