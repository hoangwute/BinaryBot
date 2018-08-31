package com.wuochoang.binarybot.ui.binary.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.olddog.common.ToastUtils;
import com.wuochoang.binarybot.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HoangNQ on 30,August,2018
 */
public class AccountTypeDialog extends BottomSheetDialogFragment {

    @BindView(R.id.radVirtualAccount)
    RadioButton radVirtualAccount;
    @BindView(R.id.radRealAccount)
    RadioButton radRealAccount;

    private Unbinder unbinder;
    private boolean isVirtual;
    private OnAccountTypeChangeListener listener;

    public static AccountTypeDialog getInstance(boolean isVirtual, OnAccountTypeChangeListener listener) {
        AccountTypeDialog dialog = new AccountTypeDialog();
        dialog.isVirtual = isVirtual;
        dialog.listener = listener;
        return dialog;
    }

    public interface OnAccountTypeChangeListener {
        void onChange(boolean isVirtual);
    }

    @OnClick(R.id.txtDone)
    public void done() {
        listener.onChange(isVirtual);
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_account_type, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (isVirtual)
            radVirtualAccount.setChecked(true);
        else
            radRealAccount.setChecked(true);

        radRealAccount.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                isVirtual = false;
        });

        radVirtualAccount.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                isVirtual = true;
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {

        }
    }
}
