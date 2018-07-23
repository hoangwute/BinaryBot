package com.wuochoang.binarybot.di.component;

import com.wuochoang.binarybot.MainActivity;
import com.wuochoang.binarybot.di.module.ActivityModule;
import com.wuochoang.binarybot.ui.home.MainFragment;

import dagger.Subcomponent;

/**
 * Created by quyenlx on 8/9/2017.
 */

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainFragment fragment);

}
