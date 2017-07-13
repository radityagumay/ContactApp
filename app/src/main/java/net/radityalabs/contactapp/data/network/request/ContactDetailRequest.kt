package net.radityalabs.contactapp.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by radityagumay on 4/16/17.
 */

open class ContactDetailRequest {

    @SerializedName("first_name")
    @Expose
    var firstName: String
    @SerializedName("last_name")
    @Expose
    var lastName: String
    @SerializedName("profile_pic")
    @Expose
    var profilePic: String
    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String
    @SerializedName("email")
    @Expose
    var email: String

    class Builder : ContactDetailRequest() {
        fun setFirstName(firstName: String): Builder {
            this.firstName = firstName
            return this
        }

        fun setLastName(lastName: String): Builder {
            this.lastName = lastName
            return this
        }

        fun setProfilePic(profilePic: String): Builder {
            this.profilePic = profilePic
            return this
        }

        fun setPhoneNumber(phoneNumber: String): Builder {
            this.phoneNumber = phoneNumber
            return this
        }

        fun setEmail(email: String): Builder {
            this.email = email
            return this
        }

        fun build(): ContactDetailRequest {
            val request = ContactDetailRequest()
            request.firstName = this.firstName
            request.lastName = this.lastName
            request.profilePic = this.profilePic
            request.phoneNumber = this.phoneNumber
            request.email = this.email
            return request
        }
    }
}
