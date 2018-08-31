package com.wuochoang.binarybot;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.base.BaseActivity;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.ui.home.MainFragment;

public class MainActivity extends BaseActivity {

    public MainFragment mainFragment;


    @Override
    public BaseFragment initFragment() {

        if (mainFragment == null)
            mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public void changeFitWindown() {
        if (mainFragment != null)
            mainFragment.changeFitWindow(-1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {
//        mainFragment.onBackPress();
        if (mainFragment != null && !mainFragment.onBackPress()) {
            ToastUtils.show("Exit app");
        }
    }
}
