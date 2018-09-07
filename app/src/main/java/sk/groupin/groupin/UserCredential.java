package sk.groupin.groupin;

import android.text.TextUtils;

import okhttp3.Credentials;

public class UserCredential {

    String username="mfi3", password="cokoladka";

    String credential = Credentials.basic(username,password);

    }


