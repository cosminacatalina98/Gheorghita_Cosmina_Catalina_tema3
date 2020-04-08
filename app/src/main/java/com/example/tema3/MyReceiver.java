package com.example.tema3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID=0;
    private static final String PRIMART_CHANNEL_ID="primary_notificasion_channel";
    private NotificationManager mnotificationManager;

    public MyReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mnotificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);

    }

    private void deliverNotification(Context context){
        Intent contentIntent=new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent=PendingIntent.getActivity(context,NOTIFICATION_ID, contentIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,PRIMART_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_fr3)
                //.setContentTitle(title)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mnotificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}
