package com.home.broadcastdemo.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class DynamicBroadcastReceiver extends BaseBroadcastReceiver {

    private IntentFilter intentFilter;

    @Override
    public void onReceive(Context context, Intent intent) {
    }

    @Override
    public IntentFilter getIntentFilter() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.home.broadcastdemo.action.isAttackMax");
        intentFilter.addAction("com.home.broadcastdemo.action.isDefensiveMax");
        intentFilter.addAction("com.home.broadcastdemo.action.isLightMax");
        intentFilter.addAction("com.home.broadcastdemo.action.isQualificationMax");
        return intentFilter;
    }
}
