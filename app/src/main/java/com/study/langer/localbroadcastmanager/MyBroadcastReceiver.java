package com.study.langer.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by huangpeiaing on 15/8/18.
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Constants.BROADCAST_SEND_MSG)) {
            Log.v("MyBroadcastReceiver", "content:" + intent.getStringExtra("content"));
            Toast.makeText(context, intent.getStringExtra("content"), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "", Toast.LENGTH_LONG).show();

        }
    }
}
