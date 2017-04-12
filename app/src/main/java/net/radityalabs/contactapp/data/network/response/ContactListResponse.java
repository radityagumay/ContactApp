package net.radityalabs.contactapp.data.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactListResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("favorite")
    @Expose
    public boolean isFavorite;
    @SerializedName("url")
    @Expose
    public String detailUrl;

    public ContactListResponse() {

    }

    protected ContactListResponse(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        profilePic = in.readString();
        isFavorite = in.readByte() != 0;
        detailUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(profilePic);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeString(detailUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactListResponse> CREATOR = new Creator<ContactListResponse>() {
        @Override
        public ContactListResponse createFromParcel(Parcel in) {
            return new ContactListResponse(in);
        }

        @Override
        public ContactListResponse[] newArray(int size) {
            return new ContactListResponse[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactListResponse)) return false;
        ContactListResponse that = (ContactListResponse) o;
        if (id != that.id) return false;
        if (isFavorite != that.isFavorite) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;
        if (profilePic != null ? !profilePic.equals(that.profilePic) : that.profilePic != null)
            return false;
        return detailUrl != null ? detailUrl.equals(that.detailUrl) : that.detailUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (profilePic != null ? profilePic.hashCode() : 0);
        result = 31 * result + (isFavorite ? 1 : 0);
        result = 31 * result + (detailUrl != null ? detailUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactListResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", isFavorite=" + isFavorite +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
