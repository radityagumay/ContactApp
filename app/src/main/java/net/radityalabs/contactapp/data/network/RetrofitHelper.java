package net.radityalabs.contactapp.data.network;

/**
 * Created by radityagumay on 4/11/17.
 */

public class RetrofitHelper {

    private RestService mRestService;

    public RetrofitHelper(RestService restService) {
        this.mRestService = restService;
    }

    /* default rest service */
    public RestService getRestService() {
        return mRestService;
    }
}
