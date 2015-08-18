package com.study.langer.localbroadcastmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by huangpeiqinag on 15/8/18.
 */
public class MyOrderBroadcastReceiverA extends BroadcastReceiver{
    private String DEBUG = MyOrderBroadcastReceiverA.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
//        abortBroadcast();//终止广播向下传输
        Log.v(DEBUG, "this is A:msg" + intent.getStringExtra("msg"));
//        intent.putExtra("next_msg", DEBUG + "给下一个广播接收者的消息");//这种方法不可行，下一个广播接收者取出消息为空
        setResultData(DEBUG + "给下一个广播接收者的消息");
    }
}
