package net.radityalabs.contactapp.presentation.util;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by radityagumay on 4/16/17.
 */

public class PhoneUtil {

    public static Intent sms(String phoneNumber) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("address", phoneNumber.trim());
        return intent;
    }

    public static Intent call(String phoneNumber) {
        String uri = "tel:" + phoneNumber.trim();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        return intent;
    }

    public static Intent email(String address) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        email.putExtra(Intent.EXTRA_TEXT, "message");
        email.setType("message/rfc822");
        return email;
    }
}
