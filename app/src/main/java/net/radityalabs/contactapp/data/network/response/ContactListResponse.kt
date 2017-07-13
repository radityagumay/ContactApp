package net.radityalabs.contactapp.data.network.response

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by radityagumay on 4/12/17.
 */

class ContactListResponse : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("profile_pic")
    @Expose
    var profilePic: String? = null
    @SerializedName("favorite")
    @Expose
    var isFavorite: Boolean = false
    @SerializedName("url")
    @Expose
    var detailUrl: String? = null

    constructor() {

    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        firstName = `in`.readString()
        lastName = `in`.readString()
        profilePic = `in`.readString()
        isFavorite = `in`.readByte().toInt() != 0
        detailUrl = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(profilePic)
        dest.writeByte((if (isFavorite) 1 else 0).toByte())
        dest.writeString(detailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is ContactListResponse) return false
        val that = o as ContactListResponse?
        if (id != that!!.id) return false
        if (isFavorite != that.isFavorite) return false
        if (if (firstName != null) firstName != that.firstName else that.firstName != null)
            return false
        if (if (lastName != null) lastName != that.lastName else that.lastName != null)
            return false
        if (if (profilePic != null) profilePic != that.profilePic else that.profilePic != null)
            return false
        return if (detailUrl != null) detailUrl == that.detailUrl else that.detailUrl == null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (firstName != null) firstName!!.hashCode() else 0
        result = 31 * result + if (lastName != null) lastName!!.hashCode() else 0
        result = 31 * result + if (profilePic != null) profilePic!!.hashCode() else 0
        result = 31 * result + if (isFavorite) 1 else 0
        result = 31 * result + if (detailUrl != null) detailUrl!!.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "ContactListResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", isFavorite=" + isFavorite +
                ", detailUrl='" + detailUrl + '\'' +
                '}'
    }

    companion object {

        val CREATOR: Parcelable.Creator<ContactListResponse> = object : Parcelable.Creator<ContactListResponse> {
            override fun createFromParcel(`in`: Parcel): ContactListResponse {
                return ContactListResponse(`in`)
            }

            override fun newArray(size: Int): Array<ContactListResponse> {
                return arrayOfNulls(size)
            }
        }
    }
}
