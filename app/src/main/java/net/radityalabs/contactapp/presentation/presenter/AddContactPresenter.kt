package net.radityalabs.contactapp.presentation.presenter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher

import net.radityalabs.contactapp.R
import net.radityalabs.contactapp.data.network.request.ContactDetailRequest
import net.radityalabs.contactapp.data.network.response.ContactDetailResponse
import net.radityalabs.contactapp.domain.usecase.AddContactUseCase
import net.radityalabs.contactapp.presentation.annotation.PermissionType
import net.radityalabs.contactapp.presentation.presenter.contract.AddContactContract
import net.radityalabs.contactapp.presentation.rx.RxPresenter
import net.radityalabs.contactapp.presentation.util.BuildUtil
import net.radityalabs.contactapp.presentation.util.FileUtil
import net.radityalabs.contactapp.presentation.util.IntentUtil
import net.radityalabs.contactapp.presentation.util.PermissionUtil

import javax.inject.Inject

import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by radityagumay on 4/16/17.
 */

class AddContactPresenter @Inject
constructor(private val useCase: AddContactUseCase) : RxPresenter<AddContactContract.View>(), AddContactContract.Presenter {

    private var firstName = ""
    private var lastName = ""
    private var email = ""
    private var phoneNumber = ""
    private val profilePic = ""

    fun composetWatcherListerner(id: Int): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                when (id) {
                    R.id.et_name -> {
                        firstName = s.toString()
                        lastName = firstName
                    }
                    R.id.et_phone -> {
                        phoneNumber = s.toString()
                    }
                    R.id.et_email -> {
                        email = s.toString()
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    fun saveAddProfile() {
        val disposable = useCase.addNewContact(request(
                firstName, lastName, email, phoneNumber, profilePic))
                .subscribe({ mView!!.addContactSuccess("Berhasil menambah kontak") }) { throwable -> mView!!.showError(throwable.message) }
        addDisposable(disposable)
    }

    fun getDetailContact(id: Int) {
        val disposal = useCase.getUserDetail(id)
                .subscribe({ response -> mView!!.onGetDetailContactSuccess(response) }) { throwable -> mView!!.showError(throwable.message) }
        addDisposable(disposal)
    }

    fun animateTimer(): Single<Long> {
        return useCase.animateTimer()
    }

    fun saveEditProfile(body: ContactDetailResponse) {
        val disposable = useCase.editContact(
                body.id, request(
                if (!TextUtils.isEmpty(firstName)) firstName else body.firstName,
                if (!TextUtils.isEmpty(lastName)) lastName else body.lastName,
                if (!TextUtils.isEmpty(email)) email else body.email,
                if (!TextUtils.isEmpty(phoneNumber)) phoneNumber else body.phoneNumber,
                if (!TextUtils.isEmpty(profilePic)) profilePic else body.profilePic))
                .subscribe({ mView!!.addContactSuccess("Berhasil merubah kontak") }) { throwable -> mView!!.showError(throwable.message) }
        addDisposable(disposable)
    }

    private fun request(firstName: String, lastName: String, email: String, phoneNumber: String, profilePic: String): ContactDetailRequest {
        return ContactDetailRequest.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setProfilePic(profilePic).build()
    }

    fun requestPermission(activity: Activity, @PermissionType type: Int) {
        if (BuildUtil.isAndroidM) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PermissionUtil.camera(), type)
            } else {
                mView!!.onPermissionGranted(type)
            }
        } else {
            mView!!.onPermissionGranted(type)
        }
    }

    fun openMediaGallery(activity: Activity) {
        activity.startActivityForResult(Intent.createChooser(IntentUtil.mediaFromGallery, "Select Media"), FileUtil.SELECT_MEDIA_FROM_GALLERY)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent, context: Context) {
        when (requestCode) {
            FileUtil.SELECT_MEDIA_FROM_GALLERY -> {
                if (resultCode == Activity.RESULT_OK) {
                    pickMediaOnGallery(data, context)
                }
            }
        }
    }

    private fun pickMediaOnGallery(data: Intent?, context: Context) {
        if (data != null) {
            val mimeType = FileUtil.getMimeType(data, context)
            if (mimeType.startsWith("image")) {
                mView!!.onSuccessPickMedia(data)
            }
        } else {
            mView!!.onErrorPickImage(Throwable("RESULT NOT OK "))
        }
    }

    companion object {

        private val TAG = AddContactPresenter::class.java!!.getSimpleName()
    }
}
