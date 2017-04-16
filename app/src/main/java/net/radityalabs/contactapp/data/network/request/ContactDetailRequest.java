package net.radityalabs.contactapp.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by radityagumay on 4/16/17.
 */

public class ContactDetailRequest {

    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;
    @SerializedName("email")
    @Expose
    public String email;

    public static class Builder extends ContactDetailRequest {
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setProfilePic(String profilePic) {
            this.profilePic = profilePic;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactDetailRequest build() {
            ContactDetailRequest request = new ContactDetailRequest();
            request.firstName = this.firstName;
            request.lastName = this.lastName;
            request.profilePic = this.profilePic;
            request.phoneNumber = this.phoneNumber;
            request.email = this.email;
            return request;
        }
    }
}
