package net.radityalabs.contactapp.data.realm;

import net.radityalabs.contactapp.BuildConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by radityagumay on 4/12/17.
 */

public class RealmHelper {

    private static final String DATABASE_NAME = "Contact.realm";
    private static final int SCHEMA_VERSION = 1;

    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getInstance(buildRealmConfiguration());
    }

    public Realm getRealm() {
        return mRealm;
    }

    public RealmConfiguration buildRealmConfiguration() {
        if (BuildConfig.DEBUG) {
            return new RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .schemaVersion(SCHEMA_VERSION)
                    .deleteRealmIfMigrationNeeded()
                    .rxFactory(new RealmObservableFactory())
                    .build();
        } else {
            byte[] key = BuildConfig.REALM_KEY.getBytes();
            return new RealmConfiguration.Builder()
                    .name(DATABASE_NAME)
                    .schemaVersion(SCHEMA_VERSION)
                    .encryptionKey(key)
                    .rxFactory(new RealmObservableFactory())
                    .build();
        }
    }
}
