package com.example.jonib.firebasepushexample;

/**
 * Created by jonib on 12/18/2017.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized MyNotificationManager getmInstance(Context context){
        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body);
        Toast.makeText(this.mCtx, "Push Working", Toast.LENGTH_LONG).show();
        Intent resultIntent = new Intent(mCtx, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr = (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }

    }

}
