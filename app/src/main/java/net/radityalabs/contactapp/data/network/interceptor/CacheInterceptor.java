package net.radityalabs.contactapp.data.network.interceptor;

import net.radityalabs.contactapp.presentation.util.ConnectionUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by radityagumay on 5/3/17.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!ConnectionUtil.isNetworkConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (ConnectionUtil.isNetworkConnected()) {
            int maxAge = 0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
