package com.wuochoang.binarybot.ui.home.tabs;

import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.ui.binary.view.BinaryFragment;
import com.wuochoang.binarybot.ui.devices.DevicesFragment;

/**
 * Created by quyenlx on 8/9/2017.
 */

public class Tab1Fragment extends BaseTabFragment {
    @Override
    public BaseFragment initFragment() {
        return new BinaryFragment();
    }

//
//    @Override
//    public void onBackStackChanged() {
//        Timber.i("Size Tab1 : " + frm.getBackStackEntryCount());
//        Fragment fragment = frm.findFragmentById(R.id.tab_container);
//        if (fragment != null)
//            Timber.i("Fragment Tab1 : " + fragment.getClass().getSimpleName());
//    }
}
