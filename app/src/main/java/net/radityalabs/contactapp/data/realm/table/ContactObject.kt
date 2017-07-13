package net.radityalabs.contactapp.data.realm.table

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

/**
 * Created by radityagumay on 4/12/17.
 */

open class ContactObject : RealmObject() {

    @PrimaryKey
    var id: Long = 0
    @Index
    var firstName: String? = null
    var lastName: String? = null
    var profilePic: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    var detailUrl: String? = null
    @Index
    var isFavorite: Boolean = false
    var isCompleted: Boolean = false

    companion object {

        @Ignore
        val ID = "id"
        @Ignore
        val FIRST_NAME = "firstName"
        @Ignore
        val IS_FAVORITE = "isFavorite"
    }
}
