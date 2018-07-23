package com.wuochoang.binarybot.di.component;

import com.wuochoang.binarybot.di.module.ActivityModule;
import com.wuochoang.binarybot.di.module.ApplicationModule;
import com.wuochoang.binarybot.di.module.NetworkModule;
import com.wuochoang.binarybot.di.module.StorageModule;
import com.wuochoang.binarybot.network.MyServiceInterceptor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quyenlx on 8/9/2017.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        StorageModule.class
})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule module);

    void inject(MyServiceInterceptor myServiceInterceptor);

}
