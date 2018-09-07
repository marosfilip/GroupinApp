package sk.groupin.groupin;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();


        if (remoteMessage.getData().size()>0){
            String link = remoteMessage.getData().get("link");
            Intent intent = new Intent("com.FCM-MESSAGE");

            intent.putExtra("link",link);
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(intent);

        }

        MyNotificationManager.getIstance(getApplicationContext())
                .displayNotification(title, body);

    }
}
