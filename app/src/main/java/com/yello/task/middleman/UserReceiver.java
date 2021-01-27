package com.yello.task.middleman;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;


public class UserReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.yello.task.middleman".equals((intent.getAction()))) {
            JSONObject userObject;
            try {
                userObject = new JSONObject(intent.getStringExtra("com.yello.task.middleman"));

                Toast.makeText(context, "(Middle Man App)\nData recieved" , Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Hello there \nUser (" + userObject.getString("name")
                        + ")has been received from Emitter App \nSend it to 3rd App")
                        .setPositiveButton("yes", (dialog, which) -> {
                            Intent intent1 = new Intent();
                            intent1.setAction("com.yello.task.3");
                            intent1.putExtra("com.yello.task.3", userObject.toString());
                            context.sendBroadcast(intent1);
                        })
                        .setNegativeButton("no", null)
                        .setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
