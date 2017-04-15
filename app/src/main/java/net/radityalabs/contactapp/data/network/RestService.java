package net.radityalabs.contactapp.data.network;

import net.radityalabs.contactapp.data.network.response.ContactDetailResponse;
import net.radityalabs.contactapp.data.network.response.ContactListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by radityagumay on 4/11/17.
 */

public interface RestService {

    @GET(ApiConstant.GET_CONTACT_LIST)
    Single<List<ContactListResponse>> getContactList();

    @GET(ApiConstant.GET_CONTACT_DETAIL)
    Single<ContactDetailResponse> getContactDetail(
            @Path("id") int userId
    );
}
