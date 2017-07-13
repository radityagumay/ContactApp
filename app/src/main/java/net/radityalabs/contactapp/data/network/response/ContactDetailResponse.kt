package net.radityalabs.contactapp.data.network.response

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by radityagumay on 4/15/17.
 */

class ContactDetailResponse : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("first_name")
    @Expose
    var firstName: String
    @SerializedName("last_name")
    @Expose
    var lastName: String
    @SerializedName("email")
    @Expose
    var email: String
    @SerializedName("phone_number")
    @Expose
    var phoneNumber: String
    @SerializedName("profile_pic")
    @Expose
    var profilePic: String
    @SerializedName("favorite")
    @Expose
    var isFavorite: Boolean = false

    constructor() {

    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        firstName = `in`.readString()
        lastName = `in`.readString()
        email = `in`.readString()
        phoneNumber = `in`.readString()
        profilePic = `in`.readString()
        isFavorite = `in`.readByte().toInt() != 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(email)
        dest.writeString(phoneNumber)
        dest.writeString(profilePic)
        dest.writeByte((if (isFavorite) 1 else 0).toByte())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        val CREATOR: Parcelable.Creator<ContactDetailResponse> = object : Parcelable.Creator<ContactDetailResponse> {
            override fun createFromParcel(`in`: Parcel): ContactDetailResponse {
                return ContactDetailResponse(`in`)
            }

            override fun newArray(size: Int): Array<ContactDetailResponse> {
                return arrayOfNulls(size)
            }
        }
    }
}
