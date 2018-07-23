package com.wuochoang.binarybot;

import com.wuochoang.binarybot.base.BaseActivity;
import com.wuochoang.binarybot.di.component.ApplicationComponent;
import com.wuochoang.binarybot.di.module.ApplicationModule;
import com.wuochoang.binarybot.di.component.DaggerApplicationComponent;
import com.olddog.common.AppCommon;


/**
 * Created by quyenlx on 8/8/2017.
 */

public class App extends AppCommon {
    private static App instance;
    private ApplicationComponent component;
    private BaseActivity currentActivity;

    public synchronized static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
