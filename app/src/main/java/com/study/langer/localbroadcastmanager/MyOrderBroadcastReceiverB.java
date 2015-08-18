package com.study.langer.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by huangpeiqiang on 15/8/18.
 */
public class MyOrderBroadcastReceiverB extends BroadcastReceiver{
    private String DEBUG = MyOrderBroadcastReceiverB.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(DEBUG, "this is B:msg" + intent.getStringExtra("msg") + intent.getStringExtra("next_msg") + getResultData());
        setResultData(DEBUG + "给下一个广播接收者的消息");
    }
}
