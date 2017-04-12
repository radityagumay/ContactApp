package net.radityalabs.contactapp.data.network;

import net.radityalabs.contactapp.data.network.response.ContactListResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by radityagumay on 4/11/17.
 */

public interface RestService {

    @GET(ApiConstant.GET_CONTACT_LIST)
    Flowable<List<ContactListResponse>> getContactList();
}