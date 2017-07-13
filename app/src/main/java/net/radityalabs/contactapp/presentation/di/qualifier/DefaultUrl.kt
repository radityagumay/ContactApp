package net.radityalabs.contactapp.presentation.di.qualifier

import java.lang.annotation.Documented
import java.lang.annotation.Retention

import javax.inject.Qualifier

import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by radityagumay on 4/11/17.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
annotation class DefaultUrl
