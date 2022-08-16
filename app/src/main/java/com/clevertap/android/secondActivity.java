package com.clevertap.android;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String actionId = extras.getString("actionId");
            if (actionId != null) {

                boolean autoCancel = extras.getBoolean("autoCancel", true);
                int notificationId = extras.getInt("notificationId", -1);
                if (autoCancel && notificationId > -1) {
                    NotificationManager notifyMgr =
                            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notifyMgr.cancel(notificationId); // the bit that cancels the notification
                }
                Toast.makeText(getBaseContext(),"Action ID is: "+actionId,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
