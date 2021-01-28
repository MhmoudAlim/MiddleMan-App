package com.yello.task.middleman;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;


public class UserReceiver extends BroadcastReceiver {
    Intent intent1;

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.yello.task.middleman".equals((intent.getAction()))) {
            JSONObject userObject;
            try {
                userObject = new JSONObject(intent.getStringExtra("com.yello.task.middleman"));

                Toast.makeText(context, "(Middle Man App)\nData received, Navigate to the App now!" , Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Hello there \nUser (" + userObject.getString("name")
                        + ") has been received from Emitter App \n Send it to Receiver App")
                        .setPositiveButton("yes", (dialog, which) -> {
                            intent1 = new Intent();
                            intent1.setAction("com.yello.task.emitter");
                            intent1.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                            intent1.putExtra("com.yello.task.emitter", userObject.toString());
                            context.sendBroadcast(intent1);
                        })
                        .setNegativeButton("no", null)
                        .setCancelable(true);
                AlertDialog alert = builder.create();
                alert.show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if ("com.yello.task.MiddleMan.response".equals((intent.getAction()))){

            Toast.makeText(context, "(Middle Man App)\nResponse From Receiver APP : SUCCESS!" , Toast.LENGTH_SHORT).show();
            intent1.setAction("com.yello.task.emitter.response");
            intent1.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            context.sendBroadcast(intent1);
        }
    }
}
