package net.radityalabs.contactapp.presentation.util

import android.content.Intent
import android.net.Uri

/**
 * Created by radityagumay on 4/16/17.
 */

object PhoneUtil {

    fun sms(phoneNumber: String): Intent {
        val intent = Intent(android.content.Intent.ACTION_VIEW)
        intent.type = "vnd.android-dir/mms-sms"
        intent.putExtra("address", phoneNumber.trim { it <= ' ' })
        return intent
    }

    fun call(phoneNumber: String): Intent {
        val uri = "tel:" + phoneNumber.trim { it <= ' ' }
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse(uri)
        return intent
    }

    fun email(address: String): Intent {
        val email = Intent(Intent.ACTION_SENDTO)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        email.putExtra(Intent.EXTRA_SUBJECT, "subject")
        email.putExtra(Intent.EXTRA_TEXT, "message")
        email.type = "message/rfc822"
        return email
    }
}
