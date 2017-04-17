package net.radityalabs.contactapp.data.network;

import net.radityalabs.contactapp.data.network.request.ContactDetailRequest;
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by radityagumay on 4/11/17.
 */

public interface RestService {

    @GET(ApiConstant.CONTACT)
    Single<List<ContactListResponse>> getContactList();

    @GET(ApiConstant.CONTACT_DETAIL)
    Single<ContactDetailResponse> getContactDetail(
            @Path("id") int userId
    );

    @POST(ApiConstant.CONTACT)
    Single<ContactDetailResponse> addContact(
            @Body ContactDetailRequest request
    );

    @PUT(ApiConstant.CONTACT_DETAIL)
    Single<ContactDetailResponse> editContact(
            @Path("id") int userId,
            @Body ContactDetailRequest request
    );

    @GET(ApiConstant.CONTACT_DETAIL)
    Single<ContactDetailResponse> getUserDetail(
            @Path("id") int userId
    );
}
