package com.clevertap.android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.Application;
import com.clevertap.android.sdk.CleverTapAPI;

public class MyApplication extends Application{
    CleverTapAPI cleverTapAPI;
    CleverTapAPI getCleverTapDefaultInstance()
    {
        return getCleverTapDefaultInstance();
    }
    public void onCreate() {
        cleverTapAPI.setDebugLevel(3);
        cleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        }
}