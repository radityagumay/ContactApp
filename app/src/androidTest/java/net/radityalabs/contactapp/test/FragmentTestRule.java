package net.radityalabs.contactapp.test;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import junit.framework.Assert;

import net.radityalabs.contactapp.R;
import net.radityalabs.contactapp.presentation.ui.activity.TestActivity;

/**
 * Created by radityagumay on 4/19/17.
 */

public class FragmentTestRule<F extends Fragment> extends ActivityTestRule<TestActivity> {

    private final Class<F> mFragmentClass;
    private F mFragment;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(TestActivity.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                try {
                    mFragment = mFragmentClass.newInstance();
                    transaction.replace(R.id.container, mFragment);
                    transaction.commit();
                } catch (Exception e) {
                    Assert.fail(String.format("%s: Could not insert %s into TestActivity: %s",
                            getClass().getSimpleName(),
                            mFragmentClass.getSimpleName(),
                            e.getMessage()));
                }
            }
        });
    }

    public F getFragment() {
        return mFragment;
    }
}
