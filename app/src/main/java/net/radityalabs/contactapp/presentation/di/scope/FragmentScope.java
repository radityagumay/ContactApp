package net.radityalabs.contactapp.presentation.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by radityagumay on 4/11/17.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
