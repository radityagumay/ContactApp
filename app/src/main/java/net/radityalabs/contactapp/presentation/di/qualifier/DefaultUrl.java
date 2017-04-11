package net.radityalabs.contactapp.presentation.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by radityagumay on 4/11/17.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface DefaultUrl {

}
