package net.radityalabs.contactapp.presentation.rx.transformation;

import net.radityalabs.contactapp.presentation.ui.foundation.Dialogs;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by radityagumay on 4/12/17.
 */

public class TransformationsBehaviour implements Transformation {

    private Dialogs dialog;

    @Inject
    public TransformationsBehaviour(Dialogs dialog) {
        this.dialog = dialog;
    }

    @Override
    public <T> FlowableTransformer<T, T> loading() {
        return transforamation(null);
    }

    @Override
    public <T> FlowableTransformer<T, T> loading(String message) {
        return transforamation(message);
    }

    private <T> FlowableTransformer<T, T> transforamation(final String message) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                dialog.showNoCancelableLoading(message != null ? message : "Menunggu");
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                dialog.hideLoading();
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dialog.hideLoading();
                            }
                        });
            }
        };
    }
}
