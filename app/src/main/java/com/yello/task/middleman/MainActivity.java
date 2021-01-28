package com.yello.task.middleman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    UserReceiver userReceiver = new UserReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter("com.yello.task.middleman");
        registerReceiver(userReceiver, filter);
        IntentFilter responseFilter = new IntentFilter("com.yello.task.MiddleMan.response");
        registerReceiver(userReceiver, responseFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(userReceiver);
    }
}