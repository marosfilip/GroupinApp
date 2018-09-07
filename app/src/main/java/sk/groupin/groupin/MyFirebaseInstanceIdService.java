package sk.groupin.groupin;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TOKEN_BROADCAST = "fcmtokenbroadcast";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("MyFirebaseToken", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
        //TEST TOKEN DEVICE IS:dLyEP8zo_6k:APA91bFVje6yFNbqj696SgS8FN1gTWb3zyR5Dd0wv5xE7LpY9dBo0wUKVa7xJPHrvf4F0U2eNIDfnvBDlYKxmne2rN4Q9GLJj01lLxlLH99IERdKtq9vZlIM2_jmtm4vl40tDhEMzqOZP5fv2sf2Sm5cqg2Sxe8ZJw
        //eq4NBUwKRBI:APA91bGsEq7V9RyjV5k5JzSGd50newlk7rRB65zwdNgZ2G64SYXqf61Vogq_XJHcaUhV7N6RYWk6UW5ai4vnNTcZGhBr7sjulCIy2Urmjkq5yPq7GuM9tqkF4pidpPlGDUT34D05e4Ud


        storeToken(refreshedToken);
        getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
    }

    private void storeToken(String token){
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(token);
    }


}
