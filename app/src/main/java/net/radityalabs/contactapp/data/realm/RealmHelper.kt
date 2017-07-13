package net.radityalabs.contactapp.data.realm

import net.radityalabs.contactapp.BuildConfig

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory

/**
 * Created by radityagumay on 4/12/17.
 */

class RealmHelper {

    val realm: Realm

    init {
        realm = Realm.getInstance(buildRealmConfiguration())
    }

    fun buildRealmConfiguration(): RealmConfiguration {
        if (BuildConfig.DEBUG) {
            return RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .schemaVersion(SCHEMA_VERSION.toLong())
                    .deleteRealmIfMigrationNeeded()
                    .rxFactory(RealmObservableFactory())
                    .build()
        } else {
            val key = BuildConfig.REALM_KEY.toByteArray()
            return RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .schemaVersion(SCHEMA_VERSION.toLong())
                    .encryptionKey(key)
                    .rxFactory(RealmObservableFactory())
                    .build()
        }
    }

    companion object {

        private val DATABASE_NAME = "Contact.realm"
        private val SCHEMA_VERSION = 1
    }
}
