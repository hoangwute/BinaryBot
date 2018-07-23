package com.wuochoang.binarybot.ui.devices;


import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;

import butterknife.OnClick;
import com.wuochoang.binarybot.R;

/**
 * Created by quyenlx on 8/9/2017.
 */

public class DevicesFragment extends BaseFragment {
    @Override
    public void injectDependence() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_devices;
    }

    @Override
    public int getTitle() {
        return R.string.title_menu_device;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.add)
    public void onClicked() {
        addFragment(new DevicesFragment());
    }
}
