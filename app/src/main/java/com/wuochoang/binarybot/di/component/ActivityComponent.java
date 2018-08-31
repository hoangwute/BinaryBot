package com.wuochoang.binarybot.di.component;

import com.wuochoang.binarybot.MainActivity;
import com.wuochoang.binarybot.di.module.ActivityModule;
import com.wuochoang.binarybot.ui.binary.view.BinaryFragment;
import com.wuochoang.binarybot.ui.home.MainFragment;
import com.wuochoang.binarybot.ui.statement.StatementFragment;

import dagger.Subcomponent;

/**
 * Created by quyenlx on 8/9/2017.
 */

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainFragment fragment);

    void inject(BinaryFragment binaryFragment);

    void inject(StatementFragment statementFragment);
}
