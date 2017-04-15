package net.radityalabs.contactapp.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by radityagumay on 4/15/17.
 */

public class ContactDetailResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("favorite")
    @Expose
    public boolean isFavorite;

    public ContactDetailResponse() {

    }

    protected ContactDetailResponse(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        profilePic = in.readString();
        isFavorite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeString(profilePic);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactDetailResponse> CREATOR = new Creator<ContactDetailResponse>() {
        @Override
        public ContactDetailResponse createFromParcel(Parcel in) {
            return new ContactDetailResponse(in);
        }

        @Override
        public ContactDetailResponse[] newArray(int size) {
            return new ContactDetailResponse[size];
        }
    };
}
