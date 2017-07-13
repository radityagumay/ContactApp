package net.radityalabs.contactapp.data.network

import net.radityalabs.contactapp.ContactApp

import java.io.File

/**
 * Created by radityagumay on 4/11/17.
 */

object ApiConstant {

    val PATH_DATA = ContactApp.instance.getCacheDir().getAbsolutePath() + File.separator + "data"
    val PATH_CACHE = PATH_DATA + "/NetCache"

    val BASE_URL = "https://api.yummly.com"
    val FOOD = "/v1/api/recipes"
    val CONTACT = "/contacts.json"
    val CONTACT_DETAIL = "/contacts/{id}.json"
}
