package com.study.langer.localbroadcastmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
        Button sendBtn = (Button) findViewById(R.id.send_broadcast);
        sendBtn.setOnClickListener(this);

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
                sendBroadcast();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    public void sendBroadcast(){
        String contentStr = content.getText().toString();
        Log.v("Debug", "contentStr:" + contentStr);
        Intent intent = new Intent(Constants.BROADCAST_SEND_MSG);
        intent.putExtra("content", contentStr);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
