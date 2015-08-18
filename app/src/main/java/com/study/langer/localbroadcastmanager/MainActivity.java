package com.study.langer.localbroadcastmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 关于安卓LocalBroadcastManager的原理和使用。
 * 参考网址：http://www.trinea.cn/android/localbroadcastmanager-impl/
 */
public class MainActivity extends Activity implements View.OnClickListener{
    private String DEBUG = MainActivity.class.getName();
    private EditText content;
    private MyBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContentView();
        initContent();
    }

    public void initContentView(){
        Button sendLocalBtn = (Button) findViewById(R.id.send_broadcast);
        sendLocalBtn.setOnClickListener(this);

        Button sendOrderBtn = (Button) findViewById(R.id.send_order_broadcast);
        sendOrderBtn.setOnClickListener(this);

        Button sendStickBtn = (Button) findViewById(R.id.send_stick_broadcast);
        sendStickBtn.setOnClickListener(this);

        Button sendOrderStickBtn = (Button) findViewById(R.id.send_stick_order_broadcast);
        sendOrderStickBtn.setOnClickListener(this);

        content = (EditText) findViewById(R.id.content);
    }

    public void initContent(){
        //注册广播接收器
        receiver = new MyBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Constants.BROADCAST_SEND_MSG));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.send_broadcast:
                sendLocalBroadcast();
                break;
            case R.id.send_order_broadcast:
                sendOrderBroadcast();
                break;
            case R.id.send_stick_broadcast:
                sendStickBroadcast();
                break;
            case R.id.send_stick_order_broadcast:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void sendLocalBroadcast(){
        String contentStr = content.getText().toString();
        Log.v("Debug", "contentStr:" + contentStr);
        Intent intent = new Intent(Constants.BROADCAST_SEND_MSG);
        intent.putExtra("content", contentStr);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void sendOrderBroadcast(){
        Intent intent = new Intent("com.study.langer.orderbroadcast");
        sendOrderedBroadcast(intent, null, new OrderedBroadcastReceiverResultReceiver(), null, Activity.RESULT_OK,
                null, null);
    }

    /**
     * 如果发送者发送了某个广播，而接收者在这个广播发送后才注册自己的Receiver，这时接收者便无法接收到刚才的广播，为此Android引入了StickyBroadcast，在广播发送结束后会保存刚刚发送的广播（Intent），这样当接收者注册完Receiver后就可以继续使用刚才的广播。如果在接收者注册完成前发送了多条相同Action的粘性广播，注册完成后只会收到一条该Action的广播，并且消息内容是最后一次广播内容。系统网络状态的改变发送的广播就是粘性广播。
     * 粘性广播通过Context的sendStickyBroadcast(Intent)接口发送，需要添加权限<uses-permission android:name="android.permission.BROADCAST_STICKY"/>
     * 也可以通过Context的removeStickyBroadcast(Intent intent)接口移除缓存的粘性广播。
     */
    public void sendStickBroadcast(){
        Intent intent = new Intent();
        sendStickyBroadcast(intent);//
    }

    Handler receiveCallBackHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v(DEBUG, "receiveCallBackHandler");
        }
    };
}
