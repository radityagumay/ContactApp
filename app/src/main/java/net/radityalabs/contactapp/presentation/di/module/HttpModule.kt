package net.radityalabs.contactapp.presentation.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import net.radityalabs.contactapp.BuildConfig
import net.radityalabs.contactapp.data.network.ApiConstant
import net.radityalabs.contactapp.data.network.RestService
import net.radityalabs.contactapp.data.network.interceptor.CacheInterceptor
import net.radityalabs.contactapp.data.network.interceptor.HeaderInterceptor
import net.radityalabs.contactapp.presentation.di.qualifier.DefaultUrl
import net.radityalabs.contactapp.presentation.util.ConnectionUtil

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by radityagumay on 4/11/17.
 */

@Module
class HttpModule {

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    internal fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @Provides
    @DefaultUrl
    internal fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return builder.baseUrl(ApiConstant.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun provideCacheInterceptor(): CacheInterceptor {
        return CacheInterceptor()
    }

    @Singleton
    @Provides
    internal fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Singleton
    @Provides
    internal fun provideClient(builder: OkHttpClient.Builder, cacheInterceptor: CacheInterceptor, headerInterceptor: HeaderInterceptor): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(headerInterceptor)
        builder.cache(setupCache())
        builder.connectTimeout(20, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }

    @Singleton
    @Provides
    internal fun provideRestService(@DefaultUrl retrofit: Retrofit): RestService {
        return retrofit.create<RestService>(RestService::class.java!!)
    }

    private fun setupCache(): Cache {
        return Cache(File(ApiConstant.PATH_CACHE), (1024 * 1024 * 50).toLong())
    }
}
