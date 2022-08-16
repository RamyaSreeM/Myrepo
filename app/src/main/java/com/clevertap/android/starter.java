package com.clevertap.android;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;

import java.util.Date;
import java.util.HashMap;

public class starter extends AppCompatActivity {
    CleverTapAPI cleverTapAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActivityLifecycleCallback.register(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        cleverTapAPI.createNotificationChannel(getApplicationContext(), "GETRAMYA", "Sample Test App", "Your Channel Description", NotificationManager.IMPORTANCE_MAX, true);

        cleverTapAPI.pushFcmRegistrationId("139132465037", true);

        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
        profileUpdate.put("Name", "Ramya");
        profileUpdate.put("Identity", 123456);
        profileUpdate.put("Email", "mjramyasree@gmail.com");
        profileUpdate.put("Phone", "+9197381718");
        profileUpdate.put("Gender", "F");
        profileUpdate.put("DOB", new Date());
        profileUpdate.put("MSG-email", true);
        profileUpdate.put("MSG-push", true);
        profileUpdate.put("MSG-sms", false);
        profileUpdate.put("MSG-whatsapp", true);
        cleverTapAPI.onUserLogin(profileUpdate);
        cleverTapAPI.pushEvent("Profile Updated");
    }

    public void sendMessage(View view) {
        Toast.makeText(this, "trigger event", Toast.LENGTH_SHORT).show();
        HashMap<String, Object> productViewedProp = new HashMap<String, Object>();
        productViewedProp.put("Product Id", "1");
        productViewedProp.put("Product Image", "https://d35f082ficw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg");
        productViewedProp.put("Product Name", "CleverTap");
        cleverTapAPI.pushEvent("Product Viewed", productViewedProp);
    }

    public void pushMessage(View view) {
        Intent nextPage = new Intent(this, secondActivity.class);
        HashMap<String, Object> notificationdet = new HashMap<String, Object>();
        notificationdet.put("AddNotify", "1");
        cleverTapAPI.pushEvent("Notification Viewed status", notificationdet);
        cleverTapAPI.pushNotificationClickedEvent(nextPage.getExtras());
        nextPage.putExtra("actionId", "test");
        startActivity(nextPage);
    }

    @Override
    protected void onNewIntent(final Intent intent) {

        super.onNewIntent(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            cleverTapAPI.pushNotificationClickedEvent(intent.getExtras());
        }
    }
}