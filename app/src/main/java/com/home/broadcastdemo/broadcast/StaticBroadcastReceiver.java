package com.home.broadcastdemo.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.home.broadcastdemo.MainActivity;

public class StaticBroadcastReceiver extends BaseBroadcastReceiver {

    private SharedPreferences sharedPreferences;
    private boolean isAutomaticallyOpen;

    @Override
    public void onReceive(Context context, Intent intent) {

        /** 读取数据 */
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        isAutomaticallyOpen = sharedPreferences.getBoolean("isAutomaticallyOpen", false);

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            if (isAutomaticallyOpen) {
                Intent mainActivityIntent = new Intent(context, MainActivity.class);                // 要启动的Activity
                mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                      // 非Activity的途徑啟動Activity時必然不存在一個Activity的棧, 所以要新建一個Activity棧來存放要啟動的Activity
                context.startActivity(mainActivityIntent);
            }
            return;
        }
    }
}
