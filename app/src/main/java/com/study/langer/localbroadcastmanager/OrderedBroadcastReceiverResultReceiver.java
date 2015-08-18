package com.study.langer.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by bigfather on 15/8/18.
 */
public class OrderedBroadcastReceiverResultReceiver extends BroadcastReceiver{
    private String DEBUG = OrderedBroadcastReceiverResultReceiver.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(DEBUG, "回执处理" + getResultData());
    }
}
