package net.radityalabs.contactapp.data.realm.table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by radityagumay on 4/12/17.
 */

public class ContactObject extends RealmObject {

    @PrimaryKey
    public long id;
    public String firstName;
    public String lastName;
    public String profilePic;
    public boolean isFavorite;
    public String detailUrl;
}
