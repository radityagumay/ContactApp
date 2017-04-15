package net.radityalabs.contactapp.data.realm.table;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactObject extends RealmObject {

    @Ignore
    public static final String ID = "id";
    @Ignore
    public static final String FIRST_NAME = "firstName";
    @Ignore
    public static final String IS_FAVORITE = "isFavorite";

    @PrimaryKey
    public long id;
    @Index
    public String firstName;
    public String lastName;
    public String profilePic;
    public String email;
    public String phoneNumber;
    public String detailUrl;
    @Index
    public boolean isFavorite;
    public boolean isCompleted;
}
