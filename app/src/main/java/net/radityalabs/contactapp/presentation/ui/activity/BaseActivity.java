package net.radityalabs.contactapp.presentation.ui.activity;

import android.support.v7.app.AppCompatActivity;

import net.radityalabs.contactapp.presentation.presenter.BasePresenter;
import net.radityalabs.contactapp.presentation.view.BaseView;

/**
 * Created by radityagumay on 4/11/17.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
}
