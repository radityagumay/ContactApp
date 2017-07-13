package net.radityalabs.contactapp.data.network

import net.radityalabs.contactapp.data.network.request.ContactDetailRequest
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.data.network.response.ContactListResponse

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Created by radityagumay on 4/11/17.
 */

interface RestService {

    @get:GET(ApiConstant.CONTACT)
    val contactList: Single<List<ContactListResponse>>

    @GET(ApiConstant.CONTACT_DETAIL)
    fun getContactDetail(
            @Path("id") userId: Int
    ): Single<ContactDetailResponse>

    @POST(ApiConstant.CONTACT)
    fun addContact(
            @Body request: ContactDetailRequest
    ): Single<ContactDetailResponse>

    @PUT(ApiConstant.CONTACT_DETAIL)
    fun editContact(
            @Path("id") userId: Int,
            @Body request: ContactDetailRequest
    ): Single<ContactDetailResponse>

    @GET(ApiConstant.CONTACT_DETAIL)
    fun getUserDetail(
            @Path("id") userId: Int
    ): Single<ContactDetailResponse>
}
