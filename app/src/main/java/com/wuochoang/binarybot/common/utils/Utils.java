package com.wuochoang.binarybot.common.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.wuochoang.binarybot.App;
import com.olddog.common.KeyboardUtils;

import com.wuochoang.binarybot.R;
import com.wuochoang.binarybot.common.Constant;
import com.wuochoang.binarybot.model.BalanceRequest;

/**
 * Created by quyenlx on 8/8/2017.
 */

public class Utils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    private Utils() {
    }

    @ColorInt
    public static int getColor(int color) {
        return ContextCompat.getColor(App.get(), color);
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static void preventDoubleClick(final View view) {
        view.setEnabled(false);
        new Handler().postDelayed(() -> {
            view.setEnabled(true);
        }, 300);
    }

    public static void setupUI(final View view, final Activity context) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    KeyboardUtils.hideSoftInput(context);
                    view.requestFocus();
                    if (view instanceof ViewGroup) {
                        if (!(view instanceof Spinner)) {
                            view.setFocusable(true);
                            view.setFocusableInTouchMode(true);
                        }
                    }
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, context);
            }
        }
    }

    public static String objectToJson(Object  object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public static String getContractType(String log) {
        String contractType = "";
        if (log.contains("CALL"))
            contractType = Constant.CONTRACT_TYPE_CALL;
        else if (log.contains("PUT"))
            contractType = Constant.CONTRACT_TYPE_PUT;
        return contractType;
    }

    public static String getCurrencyPair(String log) {
        String pair = "";
        if (log.contains("EURUSD")) {
            pair = "frxEURUSD";
        }
        if (log.contains("EURJPY")) {
            pair = "frxEURJPY";
        }
        if (log.contains("USDJPY")) {
            pair = "frxUSDJPY";
        }
        if (log.contains("USDCAD")) {
            pair = "frxUSDCAD";
        }
        if (log.contains("EURGBP")) {
            pair = "frxEURGBP";
        }
        if (log.contains("USDCAD")) {
            pair = "frxUSDCAD";
        }
        if (log.contains("USDCHF")) {
            pair = "frxUSDCHF";
        }
        if (log.contains("GBPUSD")) {
            pair = "frxGBPUSD";
        }
        return pair;
    }

}
