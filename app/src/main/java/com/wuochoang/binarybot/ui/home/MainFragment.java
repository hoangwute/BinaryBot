package com.wuochoang.binarybot.ui.home;


import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.olddog.common.KeyboardUtils;
import com.wuochoang.binarybot.App;
import com.wuochoang.binarybot.common.view.LockableViewPager;
import com.wuochoang.binarybot.manager.StackManager;
import com.wuochoang.binarybot.ui.binary.view.BinaryFragment;
import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.base.BaseFragment;
import com.wuochoang.binarybot.base.BasePresenter;
import com.wuochoang.binarybot.common.Router;
import com.wuochoang.binarybot.manager.StorageManager;
import com.wuochoang.binarybot.ui.devices.DevicesFragment;
import com.wuochoang.binarybot.ui.home.tabs.BaseTabFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by admin on 05-Mar-18.
 */

public class MainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Inject
    Router router;
    @Inject
    StorageManager storageManager;

    @BindView(R.id.container)
    ConstraintLayout mainView;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.pager)
    LockableViewPager pager;

    private StackManager manager;
    private MainVPAdapter mAdapter;

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
        pager.setAdapter(mAdapter = new MainVPAdapter(getChildFragmentManager()));
        pager.setSwipeLocked(true);
        pager.setOffscreenPageLimit(1);
        pager.setOnPageChangeListener(this);
        //initStack
        manager = new StackManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_trade);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        KeyboardUtils.hideSoftInput(mActivity);
        switch (item.getItemId()) {
            case R.id.navigation_trade:
                setCurrentItem(0);
                break;
            case R.id.navigation_statement:
                setCurrentItem(1);
                break;
        }

        return true;
    };


    @Override
    public void initData() {

    }

    public Fragment getCurrentFragment() {
        return mAdapter.getItem(pager.getCurrentItem());
    }

    public FragmentManager getCurrentFragmentMgr() {
        // getChildFragmentManager của từng tab
        return getCurrentFragment().getChildFragmentManager();
    }

    public BaseFragment getFragmentInTop() {

        if (getCurrentFragment() == null || !(getCurrentFragment() instanceof BaseTabFragment))
            return null;
        Fragment fragment = ((BaseTabFragment) getCurrentFragment()).getChildFragmentManager().findFragmentById(R.id.tab_container);
        if (!(fragment instanceof BaseFragment)) return null;
        return (BaseFragment) fragment;

    }

    public boolean onBackPress() {
        int countCurrent = getCurrentFragmentMgr().getBackStackEntryCount();
        if (countCurrent > 0) {
            getCurrentFragmentMgr().popBackStack();
            changeFitWindow(-1);
            return true;
        } else if (!manager.isEmpty()) {
            int tabBefore = manager.getTabBefore();
            if (tabBefore != -1) {
                setCurrentItem(tabBefore);
                return true;
            } else {
                return false;
            }
        } else
            return false;

    }

    public void setCurrentItem(int position) {
        pager.setCurrentItem(position, false);

    }

    public void changeFitWindow(int position) {
        if (position < 0)
            position = pager.getCurrentItem();
        boolean isFit = position == 0;
        if (isFit) {
            BaseFragment frag = getFragmentInTop();
            if (frag != null) {
                isFit = frag.isShowFitWindows();
            }
        }
        //TODO Fix
        isFit = false;
        mainView.setFitsSystemWindows(!isFit);
        if (getView() != null)
            getView().setFitsSystemWindows(!isFit);
        mainView.requestFitSystemWindows();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!isFit) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeFitWindow(position);
        getCurrentFragment().onResume();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
