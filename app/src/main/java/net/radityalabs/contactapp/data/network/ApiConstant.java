package net.radityalabs.contactapp.data.network;

import net.radityalabs.contactapp.ContactApp;

import java.io.File;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ApiConstant {

    public static final String PATH_DATA = ContactApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String BASE_URL = "http://gojek-contacts-app.herokuapp.com";
    public static final String GET_CONTACT_LIST = "/contacts.json";
    public static final String GET_CONTACT_DETAIL = "/contacts/{id}.json";

}
