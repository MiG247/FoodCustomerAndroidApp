package com.example.admin.myapplication.utils;

import android.content.Context;

/**
 * Created by GogHox on 2017/10/30.
 */

public class ScreenUtils {
    /* dp and px */
    public static int dp2px(Context ctx, float dpValue){
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dp(Context ctx, float pxValue){
        final float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    /* sp and px */
    public static int sp2px(Context ctx, float spValue){
        final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * density + 0.5f);
    }

    public static int px2sp(Context ctx, float pxValue){
        final float density = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / density + 0.5f);
    }
}
