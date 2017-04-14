package net.radityalabs.contactapp.domain.usecase;

import android.content.Context;

import net.radityalabs.contactapp.data.network.RestService;
import net.radityalabs.contactapp.data.network.RetrofitHelper;
import net.radityalabs.contactapp.data.realm.RealmHelper;

/**
 * Created by radityagumay on 4/13/17.
 */

public class ContactDetailUseCase {

    private RealmHelper realmHelper;
    private RestService service;
    private Context context;

    public ContactDetailUseCase(RetrofitHelper retrofitHelper, RealmHelper realmHelper, Context context) {
        this.service = retrofitHelper.getRestService();
        this.realmHelper = realmHelper;
        this.context = context;
    }
}
