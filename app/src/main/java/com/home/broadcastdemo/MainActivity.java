package com.home.broadcastdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.home.broadcastdemo.broadcast.DynamicBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private DynamicBroadcastReceiver dynamicBroadcastReceiver;
    private LinearLayout attackPowerLinearLayout, defensivePowerLinearLayout,
            lightWorkLinearLayout, qualificationLinearLayout;
    private CheckBox automaticallyOpenCheckBox;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean isAutomaticallyOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationSharedPreferences();
        initializationView();
        registerReceiver();
    }

    /** 初始化SharedPreferences */
    private void initializationSharedPreferences() {
        sharedPreferences =                                                                         // 创建一个SharedPreferences对象
                getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();                                                          // 实例化SharedPreferences.Editor对象
        isAutomaticallyOpen = sharedPreferences.getBoolean("isAutomaticallyOpen", false);
    }

    /** 初始化View */
    private void initializationView() {
        attackPowerLinearLayout = findViewById(R.id.attackPowerLinearLayout);
        defensivePowerLinearLayout = findViewById(R.id.defensivePowerLinearLayout);
        lightWorkLinearLayout = findViewById(R.id.lightWorkLinearLayout);
        qualificationLinearLayout = findViewById(R.id.qualificationLinearLayout);
        attackPowerLinearLayout.setVisibility(View.INVISIBLE);
        defensivePowerLinearLayout.setVisibility(View.INVISIBLE);
        lightWorkLinearLayout.setVisibility(View.INVISIBLE);
        qualificationLinearLayout.setVisibility(View.INVISIBLE);
        automaticallyOpenCheckBox = findViewById(R.id.automaticallyOpenCheckBox);
        automaticallyOpenCheckBox.setChecked(isAutomaticallyOpen);
        automaticallyOpenCheckBox.setOnClickListener(onClickListener());
    }

    /** 選擇是否開機自動執行 */
    @NonNull
    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (automaticallyOpenCheckBox.isChecked()) {
                    editor.putBoolean("isAutomaticallyOpen", true);                            // 将获取过来的值放入文件
                    editor.commit();                                                                // 提交
                } else {
                    editor.putBoolean("isAutomaticallyOpen", false);                           // 将获取过来的值放入文件
                    editor.commit();                                                                // 提交
                }
            }
        };
    }

    /** 註冊 各種數值修改的廣播 */
    private void registerReceiver() {
        dynamicBroadcastReceiver = new DynamicBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                super.onReceive(context, intent);
                if ("com.home.broadcastdemo.action.isAttackMax".equals(intent.getAction())) {
                    boolean isAttackMax = intent.getBooleanExtra(
                            "com.home.broadcastdemo.isAttackMax", false);
                    if (isAttackMax) {
                        attackPowerLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        attackPowerLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }
                if ("com.home.broadcastdemo.action.isDefensiveMax".equals(intent.getAction())) {
                    boolean isDefensiveMax = intent.getBooleanExtra(
                            "com.home.broadcastdemo.isDefensiveMax", false);
                    if (isDefensiveMax) {
                        defensivePowerLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        defensivePowerLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }
                if ("com.home.broadcastdemo.action.isLightMax".equals(intent.getAction())) {
                    boolean isLightMax = intent.getBooleanExtra(
                            "com.home.broadcastdemo.isLightMax", false);
                    if (isLightMax) {
                        lightWorkLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        lightWorkLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }
                if ("com.home.broadcastdemo.action.isQualificationMax".equals(intent.getAction())) {
                    boolean isQualificationMax = intent.getBooleanExtra(
                            "com.home.broadcastdemo.isQualificationMax", false);
                    if (isQualificationMax) {
                        qualificationLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        qualificationLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }
            }
        };
        registerReceiver(dynamicBroadcastReceiver, dynamicBroadcastReceiver.getIntentFilter());
    }
}
