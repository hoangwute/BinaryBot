package com.wuochoang.binarybot.ui.binary.view;

import com.wuochoang.binarybot.base.BaseView;

/**
 * Created by HoangNQ on 23,July,2018
 */
public interface BinaryView extends BaseView {

    void getBalance(String balance);
    void onAuthenicate(String text);
}
