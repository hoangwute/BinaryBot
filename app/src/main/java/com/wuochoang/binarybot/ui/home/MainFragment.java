package com.wuochoang.binarybot.ui.home;


import android.os.Bundle;
import android.widget.RelativeLayout;

import com.wuochoang.binarybot.ui.binary.view.BinaryFragment;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Router;
import com.wuochoang.binarybot.manager.StorageManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by admin on 05-Mar-18.
 */

public class MainFragment extends BaseFragment {

    @Inject
    Router router;
    @Inject
    StorageManager storageManager;
    @BindView(R.id.container)
    RelativeLayout mainView;

    public static MainFragment getInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void injectDependence() {
        component.inject(this);
    }

    @Override
    public void initBundle(Bundle bundle) {
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
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
        addFragment(new BinaryFragment());
    }

}
