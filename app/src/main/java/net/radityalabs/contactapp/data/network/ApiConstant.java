package net.radityalabs.contactapp.data.network;

import net.radityalabs.contactapp.ContactApp;

import java.io.File;

/**
 * Created by radityagumay on 4/11/17.
 */

public class ApiConstant {

    public static final String BASE_URL = "";

    public static final String PATH_DATA = ContactApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
